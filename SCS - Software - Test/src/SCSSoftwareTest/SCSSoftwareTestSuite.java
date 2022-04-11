package SCSSoftwareTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gui.test.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({AttendantRefillsDispensersTest.class, 
	AttendantShutDownStartupTest.class,
	AttendantTest.class, 
	BanknoteRunnerTest.class, 
	CatalogueAdderTest.class, 
	CheckoutDoneTest.class, 
	CheckoutTest.class, 
	CoinRunnerTest.class, 
	CustomerEntersBagsUsedTest.class, 
	CustomerOwnBagTest.class, 
	ItemAdderTest.class,
	//ItemNotPlaceableTest.class,
	ItemPlacerTest.class,
	MemberCardTest.class,
	PaysWithCardTest.class,
	PaysWithCashTest.class,
	PaysWithCoinTest.class,
	PLUAdderTest.class,
	PrintReceiptsCheckInkTest.class,
	PrintReceiptsCheckpaperTest.class,
	ReceiptTest.class,
	TimeoutTest.class,
	WeightCheckInBaggingareaTest.class,
	CustomerScreenTest.class,
	KeyboardTyper.class,
	SupervisionTest.class})

public class SCSSoftwareTestSuite {}
