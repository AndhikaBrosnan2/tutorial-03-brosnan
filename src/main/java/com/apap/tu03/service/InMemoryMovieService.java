package com.apap.tu03.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tu03.model.MovieModel;

@Service
public class InMemoryMovieService implements MovieService{
	private List<MovieModel> archiveMovie;
	
	public InMemoryMovieService() {
		archiveMovie = new ArrayList<>();
	}
	
	public List<MovieModel> getMovieList(){
		return archiveMovie;
	}

	@Override
	public MovieModel getMovieDetail(String id) {
		// TODO Auto-generated method stub
		
		for(MovieModel movie: archiveMovie) {
			if(id.equals(movie.getId())) {
				return movie;
			}
		}
		return null;
	}

	@Override
	public void addMovie(MovieModel movie) {
		archiveMovie.add(movie);
		// TODO Auto-generated method stub
	}

	@Override
	public MovieModel updateMovie( String id, int duration) {
		// TODO Auto-generated method stub
		for(MovieModel myMovie: archiveMovie) {
			if(id.equals(myMovie.getId())){
				myMovie.setDuration(duration);
				return myMovie;
			}
		}
		return null;
	}

	@Override
	public MovieModel deleteMovie(String id) {
		// TODO Auto-generated method stub
		for(MovieModel myMovie:archiveMovie) {
			if(id.equals(myMovie.getId())) {
				archiveMovie.remove(myMovie);
				return myMovie;
			}
		}
		return null;
	}

	@Override
	public String errorPage(String id) {
		return "data "+id+" kosong/ tidak ditemukan";
		// TODO Auto-generated method stub
		
		
	}
	
	
	

	
}
