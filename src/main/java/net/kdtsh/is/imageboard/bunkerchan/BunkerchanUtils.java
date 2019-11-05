package net.kdtsh.is.imageboard.bunkerchan;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.kdtsh.is.client.ImageboardClient;
import net.kdtsh.is.client.BunkerchanClientImpl;
import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.Imageboard;
import net.kdtsh.is.imageboard.ImageboardUtils;
import net.kdtsh.is.model.P;
import net.kdtsh.is.model.Page;
import net.kdtsh.is.model.cp.Cp;
import net.kdtsh.is.model.cp.CpPost;
import net.kdtsh.is.model.cp.CpPostProperties;
import net.kdtsh.is.model.cp.CpPostUploads;
import net.kdtsh.is.model.op.Op;
import net.kdtsh.is.model.op.OpPost;
import net.kdtsh.is.model.op.OpPostProperties;
import net.kdtsh.is.model.op.OpPostUploads;

/**
 * 
 * @author kdt
 *
 */
public class BunkerchanUtils extends ImageboardUtils {

	private static final Imageboard BUNKERCHAN_IMAGEBOARD = new BunkerchanImageboard();

	/**
	 * Get the opElement ID.
	 * 
	 * @param opElement
	 * @return
	 */
	private static BigInteger getOpId(Element opElement) {
		return new BigInteger(opElement.id());
	}

	/**
	 * Get a List of IDs for child posts.
	 * 
	 * @param innerOpElements
	 * @return
	 */
	private static List<BigInteger> getCpIdList(Elements innerOpElements) {
		List<BigInteger> cpIdList = new ArrayList<>();
		for (Element innerOpElement : innerOpElements) {
			BigInteger id = new BigInteger(innerOpElement.id());
			cpIdList.add(id);
		}
		return cpIdList;
	}

	/**
	 * Build the OP for a post given the opElement.
	 * 
	 * @param opElement
	 * @return
	 */
	private static Op buildOp(Element opElement) {

		OpPostUploads opPostUploads = new OpPostUploads();

		BigInteger id = new BigInteger(opElement.id());
		String boardName = opElement.attr("data-boarduri");

		Instant created = null;
		String name = null;
		String email = null;
		String postSubject = null;
		String threadLink = null;
		Elements namesAndEmails = opElement.getElementsByClass("linkName");
		if (!namesAndEmails.isEmpty()) {
			name = namesAndEmails.get(0).ownText();
			email = namesAndEmails.get(0).attr("href");
		}

		Elements createds = opElement.getElementsByClass("labelCreated");
		if (!createds.isEmpty()) {
			created = ZonedDateTime.of(LocalDateTime.parse(createds.get(0).text(), BUNKERCHAN_DATE_TIME_FORMATTER),
					ZoneId.systemDefault()).toInstant();
		}

		Elements postSubjects = opElement.getElementsByClass("labelSubject");
		if (!postSubjects.isEmpty()) {
			postSubject = postSubjects.get(0).text();
		}

//		Elements threadLinks = opElement.getElementsByClass("linkReply");
//		threadLink = threadLinks.get(0).attr("href");

		OpPostProperties opPostProperties = new OpPostProperties();
		opPostProperties.setCreated(created);
		opPostProperties.setEmail(email);
		opPostProperties.setName(name);
		opPostProperties.setPostSubject(postSubject);
		opPostProperties.setThreadLink(threadLink);

		String message = null;
		Elements messages = opElement.getElementsByClass("divMessage");
		if (!messages.isEmpty()) {
			message = messages.get(0).text();
		}

		OpPost opPost = new OpPost();
		opPost.setMessage(message);
		opPost.setOpPostUploads(opPostUploads);
		opPost.setOpProperties(opPostProperties);

		List<BigInteger> cpIdList = getCpIdList(opElement.getElementsByClass("postCell"));

		Op op = new Op();
		op.setBoardName(boardName);
		op.setId(id);
		op.setOpPost(opPost);
		op.setCpIdList(cpIdList);

		return op;

	}

	/**
	 * Build the CP for a post givne the opElement and the cpElement.
	 * 
	 * @param opElement
	 * @param cpElement
	 * @return
	 */
	private static Cp buildCp(Element opElement, Element cpElement) {
		BigInteger id = new BigInteger(cpElement.id());
		String boardName = cpElement.attr("data-boarduri");

		//
		// Cp Post Properties - assorted details about post
		//
		Instant created = null;
		String email = null;
		String name = null;
		Elements namesAndEmails = cpElement.getElementsByClass("linkName");
		if (!namesAndEmails.isEmpty()) {
			name = namesAndEmails.get(0).text();
			email = namesAndEmails.get(0).attr("href");
		}

		Elements createds = cpElement.getElementsByClass("labelCreated");
		if (!createds.isEmpty()) {
			created = ZonedDateTime.of(LocalDateTime.parse(createds.get(0).text(), BUNKERCHAN_DATE_TIME_FORMATTER),
					ZoneId.systemDefault()).toInstant();
		}

		CpPostProperties cpPostProperties = new CpPostProperties();
		cpPostProperties.setCreated(created);
		cpPostProperties.setEmail(email);
		cpPostProperties.setName(name);

		//
		// Cp Post Uploads - uploaded images, etc.
		//
		CpPostUploads cpPostUploads = new CpPostUploads();

		//
		// Cp Post
		//

		String message = null;
		Elements messages = cpElement.getElementsByClass("divMessage");
		if (!messages.isEmpty()) {
			message = messages.get(0).text();
		}

		CpPost cpPost = new CpPost();
		cpPost.setMessage(message);
		cpPost.setCpPostProperties(cpPostProperties);
		cpPost.setCpPostUploads(cpPostUploads);

		//
		// Cp
		//
		Cp cp = new Cp();
		cp.setId(id);
		cp.setBoardName(boardName);
		cp.setCpPost(cpPost);
		cp.setOpId(getOpId(opElement));

		return cp;

	}

	/**
	 * Custom DateTime formatter to handle the timestamps on Bunkerchan.
	 */
	private static final DateTimeFormatter BUNKERCHAN_DATE_TIME_FORMATTER;
	static {
		// manually code maps to ensure correct data always used
		// (locale data can be changed by application code)
		Map<Long, String> dow = new HashMap<>();
		dow.put(1L, "Mon");
		dow.put(2L, "Tue");
		dow.put(3L, "Wed");
		dow.put(4L, "Thu");
		dow.put(5L, "Fri");
		dow.put(6L, "Sat");
		dow.put(7L, "Sun");

		BUNKERCHAN_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder().parseCaseInsensitive().parseLenient()
				.optionalStart().appendValue(MONTH_OF_YEAR, 1, 2, SignStyle.NOT_NEGATIVE).appendLiteral('/')
				.appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE).appendLiteral('/').appendValue(YEAR, 4)
				.appendLiteral(" (").appendText(DAY_OF_WEEK, dow).appendLiteral(") ").appendValue(HOUR_OF_DAY, 2)
				.appendLiteral(':').appendValue(MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':')
				.appendValue(SECOND_OF_MINUTE, 2).optionalEnd().toFormatter();
	}

	private List<Document> getThreadDocuments(Document doc) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, URISyntaxException, ClientProtocolException, IOException {
		List<Document> documentList = new ArrayList<>();
		for (Element threadLink : doc.getElementsByClass("linkReply")) {
			String threadPathStr = threadLink.attr("href");
			URI threadPath = BUNKERCHAN_IMAGEBOARD.getURIRelativePath(threadPathStr);
			ImageboardClient client = new BunkerchanClientImpl();
			documentList.add(client.getDocument(threadPath));
		}

		return documentList;
	}

	@Override
	public Page extractPage(Document doc) {
		try {
			List<P> pList = new ArrayList<>();
			List<Document> threadList = getThreadDocuments(doc);

			for (Document thread : threadList) {
				for (Element opCell : thread.getElementsByClass("opCell")) {
					Op op = buildOp(opCell);
					pList.add(op);
					for (Element postCell : opCell.getElementsByClass("postCell")) {
						Cp cp = buildCp(opCell, postCell);
						pList.add(cp);
					}
				}
			}

			Page page = new Page();
			page.setPList(pList);
			return page;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public URL getChannelUrl(Channel channel) {

		// TODO Auto-generated method stub
		return null;
	}

}
