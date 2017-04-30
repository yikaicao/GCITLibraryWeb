package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO {
	public GenreDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();

		while (rs.next()) {
			Genre gn = new Genre();
			gn.setGenreId(rs.getInt("genre_id"));
			gn.setGenreName(rs.getString("genre_name"));
			genres.add(gn);
		}
		
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();

		while (rs.next()) {
			Genre gn = new Genre();
			gn.setGenreId(rs.getInt("genre_id"));
			gn.setGenreName(rs.getString("genre_name"));
			genres.add(gn);
		}
		return genres;
	}

	@SuppressWarnings("unchecked")
	public List<Genre> readAllGenres() throws SQLException {
		return (List<Genre>) read("select * from tbl_genre", null);
	}

}
