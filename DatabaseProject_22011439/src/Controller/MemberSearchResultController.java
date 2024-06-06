package Controller;

import View.*;
import Model.*;

public class MemberSearchResultController {
	private MemberSearchResultView view;
	private MemberSearchResultModel model;
	
	public MemberSearchResultController(MemberSearchResultView view, MemberSearchResultModel model) {
		this.view = view;
		this.model = model;
		
		view.setResults(model.getMovieDetails());
	}
	
}
