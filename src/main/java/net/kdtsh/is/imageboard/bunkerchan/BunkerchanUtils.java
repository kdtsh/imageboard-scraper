package net.kdtsh.is.imageboard.bunkerchan;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;

import java.net.URI;
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

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.kdtsh.is.model.cp.Cp;
import net.kdtsh.is.model.cp.CpPost;
import net.kdtsh.is.model.cp.CpPostProperties;
import net.kdtsh.is.model.cp.CpPostUploads;
import net.kdtsh.is.model.op.Op;
import net.kdtsh.is.model.op.OpPost;
import net.kdtsh.is.model.op.OpPostProperties;
import net.kdtsh.is.model.op.OpPostUploads;

public class BunkerchanUtils {

	public List<Op> extractOpList(Document doc) {

		Elements opCellElements = doc.getElementsByClass("opCell");

		List<Op> opList = new ArrayList<>();

		for (Element opCellElement : opCellElements) {

			//
			// Build the Cp List.
			//
			Elements innerOpElements = opCellElement.getElementsByClass("postCell");
			List<Cp> cpList = new ArrayList<>();
			for (Element innerOpElement : innerOpElements) {

				String id = innerOpElement.id();
				String dataBoardUri = innerOpElement.attr("data-boarduri");

				//
				// Cp Post Properties - assorted details about post
				//
				Instant created = null;
				String email = null;
				String name = null;
				Elements namesAndEmails = innerOpElement.getElementsByClass("linkName");
				for (Element nameAndEmail : namesAndEmails) {
					name = nameAndEmail.text();
					email = nameAndEmail.attr("href");
				}
				Elements createds = innerOpElement.getElementsByClass("labelCreated");
				for (Element createdEl : createds) {
					created = ZonedDateTime.of(LocalDateTime.parse(createdEl.text(), BUNKERCHAN_DATE_TIME_FORMATTER),
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
				Elements messages = innerOpElement.getElementsByClass("divMessage");
				for (Element messageEl : messages) {
					message = messageEl.text();
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
				cp.setDataBoardUri(dataBoardUri);
				cp.setCpPost(cpPost);

				//
				// Add the Cp to the Cp List.
				//
				cpList.add(cp);

			}

			//
			// Build the OP.
			//

			OpPostUploads opPostUploads = new OpPostUploads();

			String id = opCellElement.id();
			String dataBoardUri = opCellElement.attr("data-boarduri");

			Instant created = null;
			String name = null;
			String email = null;
			String postSubject = null;
			String threadLink = null;
			Elements namesAndEmails = opCellElement.getElementsByClass("linkName");
			for (Element nameAndEmail : namesAndEmails) {
				name = nameAndEmail.ownText();
				email = nameAndEmail.attr("href");
			}
			Elements createds = opCellElement.getElementsByClass("labelCreated");
			for (Element createdEl : createds) {
				created = ZonedDateTime.of(LocalDateTime.parse(createdEl.text(), BUNKERCHAN_DATE_TIME_FORMATTER),
						ZoneId.systemDefault()).toInstant();
			}
			Elements postSubjects = opCellElement.getElementsByClass("labelSubject");
			for (Element postSubjectEl : postSubjects) {
				postSubject = postSubjectEl.text();
			}
			Elements threadLinks = opCellElement.getElementsByClass("linkReply");
			for (Element threadLinkEl : threadLinks) {
				threadLink = threadLinkEl.text();
			}

			OpPostProperties opPostProperties = new OpPostProperties();
			opPostProperties.setCreated(created);
			opPostProperties.setEmail(email);
			opPostProperties.setName(name);
			opPostProperties.setPostSubject(postSubject);
			opPostProperties.setThreadLink(threadLink);

			String message = null;
			Elements messages = opCellElement.getElementsByClass("divMessage");
			for (Element messageEl : messages) {
				message = messageEl.text();
			}
			
			OpPost opPost = new OpPost();
			opPost.setMessage(message);
			opPost.setOpPostUploads(opPostUploads);
			opPost.setOpProperties(opPostProperties);

			Op op = new Op();
			op.setCpList(cpList);
			op.setDataBoardUri(dataBoardUri);
			op.setId(id);
			op.setOpPost(opPost);
			
			opList.add(op);

		}

		return opList;

	}

	public static final DateTimeFormatter BUNKERCHAN_DATE_TIME_FORMATTER;
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

}
