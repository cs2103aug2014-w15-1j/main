package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import logic.Task;

public class Display {
	static BasicGUI gFrame;

	public static void display(DisplayConfiguration confg) {
		if (gFrame == null) {
			lauch();
		}
		gFrame.setTitleText(confg.getTitle());
		gFrame.setFeedbackText(confg.getFeedback());
		gFrame.setMainText(processMainText(confg));

	}

	public static String processMainText(DisplayConfiguration confg) {

		String str = "";
		ArrayList<Task> taskList = confg.getTaskList();
		if (confg.isTaskView()) {
			if (taskList.size() != 1) {
				throw new Error("taskList does not contain one task exactly");
			}
			Task task = taskList.get(0);
			String blueItalicOpen = "<i font color=green>";
			String blueItalicClose = "</i> ";
			String name = blueItalicOpen + "Name" + blueItalicClose;
			String description = blueItalicOpen + "description"
					+ blueItalicClose;
			String StartDate = blueItalicOpen + "startTime" + blueItalicClose;
			String endDate = blueItalicOpen + "endTime" + blueItalicClose;

			str = "<html>" + name + task.getName() + "\n" + description
					+ task.getDescription() + "\n" + StartDate
					+ task.getStartDate() + "\n" + endDate + task.getEndDate()
					+ "\n" + "</html>";

		} else {
			String liOpen = "<li font color=blue>";
			String liClose = "</li>";
			String body = "";
			for (int i = 0; i < taskList.size(); i++) {
				body += liOpen + taskList.get(i).getName() + liClose + "\n";
			}
			str = "<html>" + "<ol>" + "\n" + body + "\n" + "</ol>" + "</html>";
		}
		return str;
	}

	public static void lauch() {
		gFrame = new BasicGUI();
		gFrame.setVisible(true);

	}

}
