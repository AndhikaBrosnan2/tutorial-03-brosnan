package com.apap.tu03.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 	
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tu03.model.MovieModel;
import com.apap.tu03.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;

	@RequestMapping("/movie/add")
	public String add(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "genre", required = true) String genre,
			@RequestParam(value = "budget", required = true) Long budget,
			@RequestParam(value = "duration", required = true) Integer duration) {
		MovieModel movie = new MovieModel(id, title, genre, budget, duration);
		movieService.addMovie(movie);
		return "add";
	}

	@RequestMapping("/movie/view")
	public String view(@RequestParam(value="id",required=false)String id, Model model) {
		
		MovieModel archieve = movieService.getMovieDetail(id);
		
		if(id==null) {
			model.addAttribute("id","error message");
			return "errorPage";
		}
		
		model.addAttribute("movie",archieve);
		return "view-movie";
	}
	
	@RequestMapping("movie/viewall")
	public String viewAll(Model model) {
		List<MovieModel> archieve = movieService.getMovieList();
		model.addAttribute("movies",archieve);
		return "viewall-movie";
	}
	
	@RequestMapping("movie/view/{id}")
	public String viewById(@PathVariable Optional<String> id,
				Model model) {
		
		//Optional membuat null & masukan salah!
		
		MovieModel archieve = movieService.getMovieDetail(id.get());
		if(archieve == null) {
//			model.addAttribute("id","error message");
			return "errorIdNotFound";
		}else {
			model.addAttribute("movie", archieve);
			return "view-movie";
		}
		
	}
	
	@RequestMapping("movie/update/{id}/duration/{duration}")
	public String updateById(@PathVariable (value = "id", required = true)String id,
							@PathVariable (value = "duration", required = true)int duration,
							Model model) {
		
		//Buat handle error belom
	
		MovieModel archieve = movieService.updateMovie(id, duration);
		model.addAttribute("movie", archieve);
		return "view-movie";
	}
	
	@RequestMapping("movie/delete/{id}")
	public String deleteById(@PathVariable(value="id",required=true)String id, Model model) {
		
		MovieModel archive = movieService.deleteMovie(id);
		model.addAttribute("movie",archive);
		return "delete-movie";
	}
}




