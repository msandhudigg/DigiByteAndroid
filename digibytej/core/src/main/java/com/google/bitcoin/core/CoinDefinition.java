package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "DigiByte";
    public static final String coinTicker = "DGB";
    public static final String coinURIScheme = "digibyte";
    public static final String cryptsyMarketId = "139";
    public static final String cryptsyMarketCurrency = "BTC";


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://altexplorer.net/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://altexplorer.net/";

    public static final String DONATION_ADDRESS = "DSZpUVTYNNB2A6H3StQkFtSmQMHJddSXhn";  //Scottlle aka xploited

    enum CoinHash {
        SHA256,
        scrypt
    };
    public static final CoinHash coinHash = CoinHash.scrypt;
    //Original Values


    public static final int TARGET_TIMESPAN = (int)(0.10 * 24 * 60 * 60);  // 72 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(1 * 60);  // 40 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //108 blocks


    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;      //108
    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN;    //72 min
    }

    public static int spendableCoinbaseDepth = 8; //main.h: static const int COINBASE_MATURITY
    //public static final int MAX_MONEY = 200000000;                 //main.h:  MAX_MONEY
    public static final String MAX_MONEY_STRING = "21000000000";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(10000000);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = Utils.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 60002;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION

    public static final boolean supportsBloomFiltering = false; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 12024;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 12025;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 30;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS

    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xfac3b6da;      //0xfa, 0xc3, 0xb6, 0xda

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1389388394L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (2447652);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "7497ea1b465eb39f1c8f507bc877078fe016d6fcb6dfad3a64c98dcc6e1e8496"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 8000;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04ffff001d01044555534120546f6461793a2031302f4a616e2f323031342c205461726765743a20446174612073746f6c656e2066726f6d20757020746f203131304d20637573746f6d657273";   //"Digitalcoin, A Currency for a Digital Age"
    static public String genessiXOutBytes = "00";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
              "seed1.digibyte.co", "seed2.hashdragon.com",
    };

    //
    // TestNet - digibyte - not tested or setup
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xfcc1b7dc;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 999999L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (99999);                         //main.cpp: LoadBlockIndex


    //main.cpp GetBlockValue(height, fee)
    public static final BigInteger GetBlockReward(int height)
    {
         int COIN = 1;
        BigInteger nSubsidy = Utils.toNanoCoins(8000, 0);

        if(height < 1440)
        {
            nSubsidy = Utils.toNanoCoins(72000, 0); //2
        }
        else if(height < 5760)
        {
            nSubsidy   = Utils.toNanoCoins(16000, 0); //2
        }
        
        nSubsidy = nSubsidy.shiftRight(height / 1051200);
       
        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 1051200;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digibyte: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";
    public static final String TESTNET_SATOSHI_KEY = "";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.bitcoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.bitcoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.bitcoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
				//checkpoints.put( 0, new Sha256Hash("0x7497ea1b465eb39f1c8f507bc877078fe016d6fcb6dfad3a64c98dcc6e1e8496"));	
    }


}
