package app.controllers.views;

import java.io.Serializable;

import app.model.views.CurrentReviewDetail;
import lombok.Builder;
import lombok.Getter;

@Builder
public class CurrentReviewView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int currentReviewId;
	
	@Getter
	private String sideA;
	
	@Getter
	private String sideB;
	
	@Getter
	private String notes;
	
	public static CurrentReviewView buildFromCurrentReviewDetail(CurrentReviewDetail currentReviewDetail) {
		return CurrentReviewView.builder()
				.currentReviewId(currentReviewDetail.getCurrentReviewId())
				.notes(currentReviewDetail.getNotes())
				.sideA(currentReviewDetail.getSideA())
				.sideB(currentReviewDetail.getSideB())
				.build();
	}
}
