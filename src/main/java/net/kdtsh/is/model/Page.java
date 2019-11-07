/**
 * Copyright (C) 2019 Kenan Toker
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.kdtsh.is.model;

import java.util.List;

import net.kdtsh.is.imageboard.Imageboard;

public class Page {

	private Imageboard imageboard;
	
	private List<P> pList;

	public Imageboard getImageboard() {
		return imageboard;
	}
	
	public void setImageboard(Imageboard imageboard) {
		this.imageboard = imageboard;
	}
	
	public List<P> getPList() {
		return pList;
	}

	public void setPList(List<P> pList) {
		this.pList = pList;
	}

}
