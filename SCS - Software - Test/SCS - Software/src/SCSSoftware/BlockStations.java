package SCSSoftware;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import java.util.ArrayList;

/**
 * This Attendant class allows attendant to block any selfcheckout station from
 * accessing hardware
 */
public class BlockStations {

	private ArrayList<SelfCheckoutStation> scsList;
	private ArrayList<SelfCheckoutStation> blockList;

	/**
	 * Constructor which takes in an array of checkoutstations as a parameter
	 * 
	 * @param scslist
	 */
	public BlockStations(ArrayList<SelfCheckoutStation> scslist) {
		this.scsList = scslist;
		this.blockList = new ArrayList<SelfCheckoutStation>();
	}

	/**
	 * This method takes in a checkout station as a parameter and adds it to an
	 * array of blocked stations
	 * 
	 * @param scs
	 */
	public void addToBlockList(SelfCheckoutStation scs) {
		blockList.add(scs);
	}

	/**
	 * This method removes all blocked checkoutstations in the blocklist.
	 */
	public void clearBlockList() {
		blockList.clear();
	}

	/**
	 * This method iterates through the arraylist and for every selfcheckoutstation
	 * contained in it, scs is then blocked
	 */
	public void blockSCSList() {
		blockList.forEach((scs) -> blockSCS(scs));
	}

	/**
	 * This method blocks all selfcheckout stations
	 */
	public void blockAll() {
		getScsList().forEach((scs) -> blockSCS(scs));
	}

	/**
	 * This method unblocks all selfcheckout stations
	 */
	public void unblockAll() {
		getScsList().forEach((scs) -> unblockSCS(scs));
	}

	/**
	 * This method disables select hardware in the selfcheckoutstation
	 */
	public void blockSCS(SelfCheckoutStation scs) {
		scs.screen.disable();
		scs.baggingArea.disable();
		scs.cardReader.disable();
		scs.mainScanner.disable();
		scs.handheldScanner.disable();
		scs.banknoteInput.disable();
		scs.coinSlot.disable();
	}

	/**
	 * This method re-enables select hardware in the selfcheckoutstation
	 */
	public void unblockSCS(SelfCheckoutStation scs) {
		scs.screen.enable();
		scs.baggingArea.enable();
		scs.cardReader.enable();
		scs.mainScanner.enable();
		scs.handheldScanner.enable();
		scs.banknoteInput.enable();
		scs.coinSlot.enable();
	}

	/**
	 * This getter method to obtain all selfcheckoutstations
	 */
	public ArrayList<SelfCheckoutStation> getScsList() {
		return scsList;
	}

	/**
	 * This getter method to obtain all blocked selfcheckoutstations
	 */
	public ArrayList<SelfCheckoutStation> getBlockList() {
		return blockList;
	}
}