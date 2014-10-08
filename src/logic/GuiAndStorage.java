package logic;

import gui.Display;
import gui.DisplayConfiguration;
import data_store.DataStore;

public class GuiAndStorage {
	public static void GuiAndStore(DisplayConfiguration passToGui, LogicToStore passToStore) {
		DataStore.writeAllData(passToStore);
		Display.display(passToGui);
	}
}
