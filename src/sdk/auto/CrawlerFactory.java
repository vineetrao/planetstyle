package sdk.auto;

import macys.MacysCrawler;
import macys.MacysParser;
import sdk.ProductParser;


public class CrawlerFactory {

	public static DailyWebsiteCrawler getCrawlerForSite(String url) throws Exception{
		
//		else if(url.contains("stalkbuylove"))
//			return new StalkBuyLoveCrawler();
//		else if(url.contains("shopnineteen"))
//			return new ShopNineteenCrawler();
//		else if(url.contains("americanswan"))
//			return new AmericanswanCrawler();
//		else if(url.contains("basicslife"))
//			return new BasicslifeCrawler();
//		else if(url.contains("chumbak"))
//			return new ChumbakCrawler();
//		else if(url.contains("cilory"))
//			return new CiloryCrawler();
//		else if(url.contains("donebynone"))
//			return new DonebynoneCrawler();
//		else if(url.contains("fabulloso"))
//			return new FabullosoCrawler();
//		else if(url.contains("fashionaffair"))
//			return new FashionaffairCrawler();
//		else if(url.contains("ladyblush"))
//			return new LadyblushCrawler();
//		else if(url.contains("mirraw"))
//			return new MirrawCrawler();
//		else if(url.contains("santana"))
//			return new SantanaCrawler();
//		else if(url.contains("sbuys.in"))
//			return new SbuysCrawler();
//		else if(url.contains("shopnineteen"))
//			return new ShopNineteenCrawler();
//		else if(url.contains("stalkbuylove"))
//			return new StalkBuyLoveCrawler();
//		else if(url.contains("voxpopclothing"))
//			return new VoxPopCrawler();
//		else if(url.contains("watchkart"))
//			return new WatchkartCrawler();
//		else if(url.contains("zivame"))
//			return new ZivameCrawler();
//		else if(url.contains("bonnito"))
//			return new BonnitoCrawler();
//		else if(url.contains("trendin.com"))
//			return new TrendinCrawler();
//		else if(url.contains("faballey"))
//			return new FaballeyCrawler();
//		else if(url.contains("fabfurnish"))
//			return new FabFurnishCrawler();
//		else if(url.contains("ethnicstation"))
//			return new EthnicstationCrawler();
//		else if(url.contains("koovs.com"))
//			return new KoovsCrawler();
//		else if(url.contains("pepperfry.com"))
//			return new PepperFryCrawler();
//		else if(url.contains("mango.com"))
//			return new MangoCrawler();
//		else if(url.contains("bewakoof"))
//			return new BewakoofCrawler();
//		else if(url.contains("indiacircus"))
//			return new IndiacircusCrawler();
//		else if(url.contains("forever21"))
//			return new Forever21Crawler();
//		else if(url.contains("yepme"))
//			return new YepmeCrawler();
//		else if(url.contains("fashionara"))
//			return new FashionaraCrawler();
//		else if(url.contains("moodsofcloe"))
//			return new MoodsOfCloeCrawler();
//		else if(url.contains("myntra"))
//			return new MyntraCrawler();
//		else if(url.contains("jabong"))
//			return new JabongCrawler();
//		else if(url.contains("zovi"))
//			return new ZoviCrawler();
//		else if(url.contains("freecultr"))
//			return new FreecultrCrawler();
//		else if(url.contains("turnstylish"))
//			return new TurnstylishCrawler();
//		else if(url.contains("thegudlook"))
//			return new ThegudlookCrawler();
//		else if(url.contains("flipkart"))
//			return new FlipkartCrawler();
		if(url.contains("macys"))
			return new MacysCrawler();
//		else if(url.contains("snapdeal"))
//			return new SnapdealCrawler();
//		else if(url.contains("femnmas"))
//			return new FemNMasCrawler();
//		else if(url.contains("vastrika"))
//			return new VastrikaCrawler();
//		else if(url.contains("jaypore"))
//			return new JayporeCrawler();
//		else if(url.contains("high5store"))
//			return new High5Crawler();
//		else if(url.contains("elitify"))
//			return new ElitifyCrawler();
//		else if(url.contains("craftsvilla"))
//			return new CraftsvillaCrawler();
//		else if(url.contains("samyakk"))
//			return new SamyakkCrawler();
//		else if(url.contains("giftpiper"))
//			return new GiftpiperCrawler();
//		else if(url.contains("urbanladder"))
//			return new UrbanladderCrawler();
//		else if(url.contains("voylla"))
//			return new VoyllaCrawler();
//		else if(url.contains("thepurplesack"))
//			return new ThePurpleSackCrawler();
//		else if(url.contains("justfordecor"))
//			return new JustfordecorCrawler();
//		else if(url.contains("veeshack"))
//			return new VeeshackCrawler();
//		else if(url.contains("giftsmate"))
//			return new GiftsmateCrawler();
//		else if(url.contains("juvalia"))
//			return new JuvaliaCrawler();
//		else if(url.contains("biba"))
//			return new BibaCrawler();
//		else if(url.contains("kaaryah"))
//			return new KaaryahCrawler();
//		else if(url.contains("shopoclick"))
//			return new ShopoclickCrawler();
//		else if(url.contains("nykaa"))
//			return new NykaaCrawler();
//		else if(url.contains("stylefiesta"))
//			return new StylefiestaCrawler();
//		else if(url.contains("flyingmachine"))
//			return new FlyingmachineCrawler();
		
		throw new Exception("No Crawler found for : " + url);
	}
	
	public static ProductParser getParserForSite(String url) throws Exception{
//		if(url.contains("stalkbuylove"))
//			return new StalkBuyLoveParser(url);
//		else if(url.contains("shopnineteen"))
//			return new ShopNineteenParser(url);
//		else if(url.contains("americanswan"))
//			return new AmericanSwanParser(url);
//		else if(url.contains("basicslife"))
//			return new BasicslifeParser(url);
//		else if(url.contains("chumbak"))
//			return new ChumbakParser(url);
//		else if(url.contains("cilory"))
//			return new CiloryParser(url);
//		else if(url.contains("donebynone"))
//			return new DonebynoneParser(url);
//		else if(url.contains("fabulloso"))
//			return new FabullosoParser(url);
//		else if(url.contains("fashionaffair"))
//			return new FashionaffairParser(url);
//		else if(url.contains("ladyblush"))
//			return new LadyblushParser(url);
//		else if(url.contains("mirraw"))
//			return new MirrawParser(url);
//		else if(url.contains("santana"))
//			return new SantanaParser(url);
//		else if(url.contains("sbuys.in"))
//			return new SbuysParser(url);
//		else if(url.contains("shopnineteen"))
//			return new ShopNineteenParser(url);
//		else if(url.contains("stalkbuylove"))
//			return new StalkBuyLoveParser(url);
//		else if(url.contains("voxpopclothing"))
//			return new VoxPopParser(url);
//		else if(url.contains("watchkart"))
//			return new WatchkartParser(url);
//		else if(url.contains("zivame"))
//			return new ZivameParser(url);
//		else if(url.contains("bonnito"))
//			return new BonnitoParser(url);
//		else if(url.contains("trendin.com"))
//			return new TrendinSingleProductParser(url);
//		else if(url.contains("faballey"))
//			return new FaballeyParser(url);
//		else if(url.contains("fabfurnish"))
//			return new FabFurnishParser(url);
//		else if(url.contains("ethnicstation"))
//			return new EthnicstationParser(url);
//		else if(url.contains("koovs.com"))
//			return new KoovsParser(url);
//		else if(url.contains("pepperfry.com"))
//			return new PepperFryParser(url);
//		else if(url.contains("mango.com"))
//			return new MangoParser(url);
//		else if(url.contains("bewakoof"))
//			return new BewakoofParser(url);
//		else if(url.contains("indiacircus"))
//			return new IndiacircusParser(url);
//		else if(url.contains("forever21"))
//			return new Forever21Parser(url);
//		else if(url.contains("yepme"))
//			return new YepmeParser(url);
//		else if(url.contains("fashionara"))
//			return new FashionaraParser(url);
//		else if(url.contains("moodsofcloe"))
//			return new MoodsOfCloeParser(url);
//		else if(url.contains("myntra"))
//			return new MyntraParser(url);
//		else if(url.contains("jabong"))
//			return new JabongParser(url);
//		else if(url.contains("zovi"))
//			return new ZoviParser(url);
//		else if(url.contains("freecultr"))
//			return new Freecultrparser(url);
//		else if(url.contains("turnstylish"))
//			return new TurnstylishParser(url);
//		else if(url.contains("thegudlook"))
//			return new ThegudlookParser(url);
//		else if(url.contains("flipkart"))
//			return new FlipkartParser(url);
		if(url.contains("macys"))
			return new MacysParser(url);
//		else if(url.contains("snapdeal"))
//			return new SnapdealParser(url);
//		else if(url.contains("femnmas"))
//			return new FemNMasParser(url);
//		else if(url.contains("vastrika"))
//			return new VastrikaParser(url);
//		else if(url.contains("jaypore"))
//			return new JayporeParser(url);
//		else if(url.contains("high5store"))
//			return new High5storeParser(url);
//		else if(url.contains("elitify"))
//			return new ElitifyParser(url);
//		else if(url.contains("craftsvilla"))
//			return new CraftsvillaParser(url);
//		else if(url.contains("samyakk"))
//			return new SamyakkParser(url);
//		else if(url.contains("giftpiper"))
//			return new GiftpiperParser(url);
//		else if(url.contains("urbanladder"))
//			return new UrbanladderParser(url);
//		else if(url.contains("voylla"))
//			return new VoyllaParser(url);
//		else if(url.contains("thepurplesack"))
//			return new ThePurpleSackParser(url);
//		else if(url.contains("justfordecor"))
//			return new JustfordecorParser(url);
//		else if(url.contains("veeshack"))
//			return new VeeshackParser(url);
//		else if(url.contains("giftsmate"))
//			return new GiftsmateParser(url);
//		else if(url.contains("juvalia"))
//			return new JuvaliaParser(url);
//		else if(url.contains("biba"))
//			return new BibaParser(url);
//		else if(url.contains("kaaryah"))
//			return new KaaryahParser(url);
//		else if(url.contains("shopoclick"))
//			return new ShopoclickParser(url);
//		else if(url.contains("nykaa"))
//			return new NykaaParser(url);
//		else if(url.contains("stylefiesta"))
//			return new StylefiestaParser(url);
//		else if(url.contains("flyingmachine"))
//			return new FlyingmachineParser(url);
		
		throw new Exception("No Parser found for : " + url);
	}
}
