package SCSSoftwareTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gui.test.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({AttendantRefillsDispensersTest.class, 
	AttendantShutDownStartupTest.class,
	AttendantTest.class, 
	BanknoteRunnerTest.class, 
	BlockStationsTest.class,
	CatalogueAdderTest.class, 
	CheckoutDoneTest.class, 
	CheckoutTest.class, 
	CoinRunnerTest.class, 
	CustomerEntersBagsUsedTest.class, 
	CustomerOwnBagTest.class, 
	ItemAdderTest.class,
	ItemPlacerTest.class,
	MemberCardTest.class,
	PaysWithCardTest.class,
	PaysWithCashTest.class,
	PLUAdderTest.class,
	PrintReceiptsCheckInkTest.class,
	PrintReceiptsCheckpaperTest.class,
	ReceiptTest.class,
	TimeoutTest.class,
	WeightCheckInBaggingareaTest.class,
	KeyboardTyper.class,
	SupervisionTest.class})

public class SCSSoftwareTestSuite {}
