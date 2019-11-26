package app.controllers;

import java.util.Optional;
import java.util.concurrent.Callable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.controllers.params.CurrentReviewParams;
import app.controllers.views.CurrentReviewView;
import app.model.views.CurrentReviewDetail;
import app.services.CurrentReviewsService;

@RestController
@RequestMapping(path = "/deck/{deckId}/currentReview")
public class CurrentReviewsController {
	@Autowired
	private CurrentReviewsService currentReviewsService;
	
	
	@GetMapping
	public Callable<ResponseEntity<CurrentReviewView>> initCurrentReview(@PathVariable int deckId) {
		return new Callable<ResponseEntity<CurrentReviewView>>() {
			@Override
			public ResponseEntity<CurrentReviewView> call() throws Exception {
				Optional<CurrentReviewDetail> currentReviewDetail = currentReviewsService.initCurrentReview(deckId);
				if (currentReviewDetail.isPresent()) {
					return ResponseEntity.ok(CurrentReviewView.buildFromCurrentReviewDetail(currentReviewDetail.get()));
				} else {
					return ResponseEntity.notFound().build();
				}
			}
		};
	}
	
	@PostMapping(path = "/value")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Callable<Void> valueCurrentReview(
			@PathVariable int deckId,
			@Valid CurrentReviewParams currentReviewParams) {
		return new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				currentReviewsService.valueCurrentReview(
					deckId,
					currentReviewParams.getCurrentReviewId(),
					currentReviewParams.getValue());
				return null;
			}
		};
	}
}
