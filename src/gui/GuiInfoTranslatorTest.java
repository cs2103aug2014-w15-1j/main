package gui;

import static org.junit.Assert.*;
import logic.DisplayInfo;
import logic.GUIStatus;
import logic.JDate;
import logic.VIEW_MODE;

import org.junit.Assert;
import org.junit.Test;

/**
 * This Junit class is designed to check the accuracy of
 * <code>GuiController</code> so that the information passed from
 * <Strong>Logic</strong> will displayed corrected
 * 
 * @author A0119391A
 *
 */
public class GuiInfoTranslatorTest {

	private GuiInfoTranslator run(VIEW_MODE mode, boolean nextPage,
			boolean previousPage, String feedback, String title, JDate date) {

		GUIStatus status = new GUIStatus(mode, nextPage, previousPage, -1, date);
		DisplayInfo displayInfo = new DisplayInfo(status, feedback, true, title);

		GuiInfoTranslator translator = new GuiInfoTranslator(displayInfo);
		return translator;
	}

	@Test
	public void test1() {

		VIEW_MODE mode = VIEW_MODE.TASK_DETAIL;
		boolean nextPage = false;
		boolean previousPage = false;
		String feedback = "dsakfsadlkfasdfaf";
		String title = "sdaf";
	

		GuiInfoTranslator translator = run(mode, nextPage, previousPage,
				feedback, title, new JDate(2004, 10, 27));

		Assert.assertTrue("view mode does not match", translator.getViewMode()
				.equals(mode));
		Assert.assertTrue("feedback does not match", translator
				.getFeedbackString().equals(feedback));
		Assert.assertTrue("title does not match", translator.getTitleString()
				.equals(title));
		Assert.assertTrue("next page does not match",
				translator.hasNextPage() == nextPage);
		Assert.assertTrue("previous page does not match",
				translator.hasPreviousPage() == previousPage);
		
	}
	
	@Test
	public void test2() {

		VIEW_MODE mode = VIEW_MODE.TASK_LIST;
		boolean nextPage = true;
		boolean previousPage = true;
		String feedback = "";
		String title = "";
	

		GuiInfoTranslator translator = run(mode, nextPage, previousPage,
				feedback, title, new JDate(2004, 10, 27));

		Assert.assertTrue("view mode does not match", translator.getViewMode()
				.equals(mode));
		Assert.assertTrue("feedback does not match", translator
				.getFeedbackString().equals(feedback));
		Assert.assertTrue("title does not match", translator.getTitleString()
				.equals(title));
		Assert.assertTrue("next page does not match",
				translator.hasNextPage() == nextPage);
		Assert.assertTrue("previous page does not match",
				translator.hasPreviousPage() == previousPage);
		
	}
	
	@Test
	public void test3() {

		VIEW_MODE mode = VIEW_MODE.TASK_LIST;
		boolean nextPage = true;
		boolean previousPage = false;
		String feedback = "";
		String title = "";
	

		GuiInfoTranslator translator = run(mode, nextPage, previousPage,
				feedback, title, new JDate(2004, 10, 27));

		Assert.assertTrue("view mode does not match", translator.getViewMode()
				.equals(mode));
		Assert.assertTrue("feedback does not match", translator
				.getFeedbackString().equals(feedback));
		Assert.assertTrue("title does not match", translator.getTitleString()
				.equals(title));
		Assert.assertTrue("next page does not match",
				translator.hasNextPage() == nextPage);
		Assert.assertTrue("previous page does not match",
				translator.hasPreviousPage() == previousPage);
		
	}
}
