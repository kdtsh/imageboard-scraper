package net.kdtsh.is.client;

import java.time.Instant;
import java.util.List;

import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.model.P;
import net.kdtsh.is.model.op.Op;

/**
 * A client for retrieving posts from an imageboard.
 * 
 * @author kdt
 *
 */
public interface Client {

	/**
	 * Get a List of {@link net.kdtsh.is.model.P P}, i.e. posts on an imageboard,
	 * whether they are original or child posts, on the first page of the board.
	 * 
	 * @param channel the channel for which to get a List of P.
	 * @return a List of P.
	 */
	public List<P> getPList(Channel channel);

	/**
	 * Get a List of {@link net.kdtsh.is.model.P P}, i.e. posts on an imageboard,
	 * whether they are original or child posts, on the whole board since a given
	 * instant in time.
	 * 
	 * @param channel      the channel for which to get a List of P.
	 * @param sinceInstant the earliest Instant for which elements of P will be
	 *                     returned.
	 * @return a List of P.
	 */
	public List<P> getPList(Channel channel, Instant sinceInstant);

	/**
	 * Get a List of {@link net.kdtsh.is.model.op.Op Op}, i.e. the original posts on
	 * an imageboard, on the first page of the board. Each Op has an associated List
	 * of {@link net.kdtsh.model.cp.Cp Cp}, i.e. the child posts of some original
	 * post on a board.
	 * 
	 * @param channel the channel for which to get a List of P.
	 * @return a List of Op.
	 */
	public List<Op> getOpList(Channel channel);

	/**
	 * Get a List of {@link net.kdtsh.is.model.op.Op Op}, i.e. the original posts on
	 * an imageboard, on the whole board since a given instant in time. Each Op has
	 * an associated List of {@link net.kdtsh.model.cp.Cp Cp}, i.e. the child posts
	 * of some original post on a board.
	 * 
	 * @param channel      the channel for which to get a List of P.
	 * @param sinceInstant the earliest Instant for which elements of Op will be
	 *                     returned.
	 * @return a List of Op.
	 */
	public List<Op> getOpList(Channel channel, Instant sinceInstant);

}
