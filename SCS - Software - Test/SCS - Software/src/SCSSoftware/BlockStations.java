package SCSSoftware;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import java.util.ArrayList;

public class BlockStations {

    private ArrayList<SelfCheckoutStation> scsList;
    private ArrayList<SelfCheckoutStation> blockList;

    public BlockStations(ArrayList<SelfCheckoutStation> scslist) {
        this.scsList = scslist;
        this.blockList = new ArrayList<SelfCheckoutStation>();
    }

    public void addToBlockList(SelfCheckoutStation scs)
    {
        blockList.add(scs);
    }

    public void clearBlockList()
    {
        blockList.clear();
    }

    public void blockSCSList()
    {
        blockList.forEach((scs) -> blockSCS(scs));
    }

    public void blockAll()
    {
        getScsList().forEach((scs) -> blockSCS(scs));
    }

    public void unblockAll()
    {
        getScsList().forEach((scs) -> unblockSCS(scs));
    }

    public void blockSCS(SelfCheckoutStation scs)
    {
        scs.screen.disable();
        scs.baggingArea.disable();
        scs.cardReader.disable();
        scs.mainScanner.disable();
        scs.handheldScanner.disable();
        scs.banknoteInput.disable();
        scs.coinSlot.disable();
    }

    public void unblockSCS(SelfCheckoutStation scs)
    {
        scs.screen.enable();
        scs.baggingArea.enable();
        scs.cardReader.enable();
        scs.mainScanner.enable();
        scs.handheldScanner.enable();
        scs.banknoteInput.enable();
        scs.coinSlot.enable();
    }

	public ArrayList<SelfCheckoutStation> getScsList() {
		return scsList;
	}
	
	public ArrayList<SelfCheckoutStation> getBlockList() {
		return blockList;
	}
}