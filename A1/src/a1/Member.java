package a1;

import java.io.Serializable;
/**
 *
 * @author Joshua
 */
public class Member implements Serializable
 {
	private String title;
	private int nbrPages;
	private double cost;


	 public Member() {

	 }
     public Member(String title,int nbrPages){
	   this.title=title;
	   this.nbrPages=nbrPages;
   }


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String viewDetails() {
		return "Title:"+title+"\nNumber of pages:"+nbrPages+"\nTotal cost:$"+cost;
	}

	public void calculateCost() {
		final double COST_PER_PAGE=0.15;

		cost=nbrPages*COST_PER_PAGE;
	}

  }

