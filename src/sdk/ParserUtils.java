package sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ParserUtils {

	public static final List<String> masterColors = Arrays.asList(
			"red", "blue", "green", "orange", "white", "black", "pink", "yellow", "grey", "peach",
			"purple", "violet", "maroon", "mono", "mustard", "floral", "navy", "beige", "brown",
			"cream", "lilac", "lavender", "gold", "lime", "teal", "mint", "rust", "metallic"
			,"ecru", "indigo", "scarlet", "olive", "khakhi");
	
	public static final Map<String, String > masterStyles = new HashMap<String, String>(){{
        put("designer","designer");
        put("casual","casual");
        put("formal","formal");
        put("office","formal");
        put("trendy","trendy");
        put("weekend","weekend");
        put("party","party");
        put("wedding","wedding");
        put("bollywood","bollywood");
        put("boho","bohemian");
        put("bohemian","bohemian");
        put("evening","evening");
        put("dinner","evening");
        put("classy","classy");
        put("western","western");
        put("summer","summer");
        put("winter","winter");
        put("rural","rural");
        put("urban","urban");
        put("funny","funny");
        put("comfort","comfort");
        put("slim","slim");
        put("sassy","sassy");
        put("cult","cult");
        put("contemporary","contemporary");
        put("printed","printed");
        put("bridal","bridal");
        put("college","college");
        put("festival","festival");
        put("hep","hep");
        put("royal","royal");
        put("relaxed","relaxed");
        put("sporty","sporty");
        put("student","student");
	}};
        
	public static final List<String> masterPersonalCareBrands = Arrays.asList(
			"dove", "revlon", "maybelline", "soulflower", "khadi", "nyassa", "l oreal",
			"paul mitchell", "schwarzkopf", "park avenue", "set wet", "brylcreem", "police",
			"bottega di lungavita", "terrake", "dermalogica", "l occitane", "thalgo", "h2o",
			"dermalogica", "vichy", "garnier", "neutrogena", "tous", "fcuk", "guess", "dkny", "lee cooper",
			"evaflor", "ulric de varens", "jaguar", "david beckham", "esprit", "tommy hilfiger",
			"sally hansen", "lakme", "colorbar", "opi", "bourjois", "konad", "basicare", "vega",
			"roots", "faces", "kent", "bare", "max factor", "nyx", "oriflame", "deborah milano",
			"street wear", "deborah", "rimmel", "kaya", "corioliss", "remington", "braun", "fx",
			"toni&guy", "organix", "kerastase", "aroma magic", "denman", "lotus", "just herbs",
			"elle 18", "swiss army", "parfum", "j del pozo", "bioderma", "gap", "barbie", "the natures co",
			"victoria s secret", "bath and body works", "soap opera", "avon", "the body shop", "omved", "fruttini",
			"grace cole", "burberry" , "ferrari" , "nina ricci","narcisco rodriguez","moschino"
			,"davidoff","chopard","sander","playboy","christian dior","cacharel","bvlgari"
			,"paco rabanne","avon","wpc","calvin klein","chanel","jean paul gaultier","hugo","gucci","adidas"
			,"nike","elizabeth arden","versace","dolce gabbana","wow","escada","anna andre","miner"
			,"ardell","asepta","elle18","gala of london","biotique","vlcc","shahnaz husain","himalaya","amway","attitude"
			,"auriga","streetwear","olivia","lipice","lord and berry","fiama","vedic line","old spice","gillette","votre"
			,"carolina herrera","yardley","nautica","issey miyake","giorgio armani","denim","yves saint laurent"
			,"lapidus","w.o.w","benetton","lacoste","kenzo", "hot wheels", "jungle");
	
	public static final List<String> masterCelebrities = Arrays.asList(
			"kareena", "deepika", "priyanka", "anushka", "katrina", "madhuri", "sridevi", "sri devi", "shilpa shetty",
			"sonakshi", "sonam", "bipasha", "aishwarya", "tamanna", "karishma", "malaika", "alia", "shradha",
			"bollywood", "ayesha", "raveena", "sushmita", "shraddha", "replica");
	
	public static final Map<String, String > masterCatsMnM = new HashMap<String, String>(){{
		put("poster","poster");
		
		put("t shirt","t shirt");
		put("t-shirt","t shirt");
		put("tshirt","t shirt");
		
		put("guitar","guitar");
		put("piano","piano");
		
		put("cd","cd");
		put("dvd","dvd");
	}};
	
	public static final Map<String, String > masterCatsKids = new HashMap<String, String>(){{
		
		put("soft toy","soft toy");
		put("softtoy","soft toy");
		put("soft-toy","soft toy");
		
		put("bedsheet","bedsheet");
		put("bed sheet","bedsheet");
		put("bed-sheet","bedsheet");
		
		put("cushion","cushion");
		
		put("cushion cover","cushion cover");
		put("cushioncover","cushion cover");
		put("cushion-cover","cushion cover");
		
		put("sunglass","sunglasses");
        put("sun glass","sunglasses");
        put("sun-glass","sunglasses");
        
        put("backpack","backpack");
        put("back pack","backpack");
        put("back-pack","backpack");
        
        put("shoe","shoes");
        put("loafer","loafer");
        put("mocassin","mocassin");
        put("boatshoes","boat shoes");
        put("boat shoes","boat shoes");
        put("boat-shoes","boat shoes");
        put("boot","boots");
        put("brogue","brogue");
        put("sneaker","sneakers");
        put("sandal","sandals");
        
        put("chappal","chappals");
        put("slipper","slippers");
        
        put("flipflop","flip flops");
        put("flip flop","flip flops");
        put("flip-flop","flip flops");
        
		put("t.shirt","t shirt");
		put("t shirt","t shirt");
        put("tshirt","t shirt");
        put("t-shirt","t shirt");
        put(" tee","t shirt");
        
        put("watch","watch");
        
        put("kurta","kurta");
        put("kurti","kurta");
        
        put("hair","hair accessories");
        
        put("necklace","jewellery");
        put("beads","jewellery");
        put("jewellery","jewellery");
        put("jewelery","jewellery");
        put("jewelry","jewellery");
        
        put("hoodie","hoodie");
        put("jacket","jacket");
        put("shrug","shrugs");
        put("cover up","cover up");
        put("coverup","cover up");
        put("cover-up","cover up");
        put("coat","coat");
        put("pant","pants");
        put("capri","capris");
        put("sweatshirt","sweatshirt");
        put("trouser","trouser");
        put("jeans","jeans");
        put("bodysuit","bodysuit");
        put("sweater","sweater");
        put("cardigan","sweater");
        
        put(" shirt","shirt");
        put("skirt","skirt");
        put("shorts","shorts");
        
        put("lehnga","lehnga");
        put("lehenga","lehnga");
        put("lehanga","lehnga");
        
        put(" top","top");
        put("blouse","blouse");
        
        put("dress","dress");
        put("frock","dress");
        put("jumper","jumper");
        put("pajama","pajama");
        put("pyjama","pajama");
        
        put("backpack","backpack");
        put("back pack","backpack");
        put("back-pack","backpack");
        
        put("bags","bags");
	}};
	
	public static final Map<String, String > masterCatsSports = new HashMap<String, String>(){{
		put("jersey","jersey");
	}};
	
	public static final Map<String, String > masterCatsElectronics = new HashMap<String, String>(){{
		put("speaker","speakers");
		
		put("mouse pad","mouse pad");
		put("mousepad","mouse pad");
		put("mouse-pad","mouse pad");
		
		put("pen drive","pen drive");
		put("pendrive","pen drive");
		put("pen-drive","pen drive");
		put("usb drive","pen drive");
		put("usbdrive","pen drive");
		put("usb-drive","pen drive");
		
		put("projector","projector");
		
		put("apple","apple");
		put("macbook","apple");
		put("mac book","apple");
		put("mac-book","apple");
		
		put("laptop skin","laptop skin");
		put("laptopskin","laptop skin");
		put("laptop-skin","laptop skin");
		
		put("samsung","samsung");
		put("sony","sony");
		
		put("headset","headset");
		put("head set","headset");
		put("head-set","headset");
		
		put("headphone","headphone");
		put("head phone","headphone");
		put("head-phone","headphone");
	}};
	
	public static final Map<String, String > masterCatsBnH = new HashMap<String, String>(){{
		
		put("powder","powder");
		
		put("shampoo","shampoo");
		
		put("foundation","foundation");
		put(" foundation","makeup");
		put("concealer","concealer");
		put(" concealer","makeup");
		put("compact","compact");
		put(" compact","makeup");
		
		put("edp","perfume");
		put("edt","perfume");
		put("perfume","perfume");
		
		put("shower gel","shower gel");
		put("soap","soap");
		put("bubble bath","bubble bath");
		
		put("cream","cream");
		
		put("makeup","makeup");
		put("make up","makeup");
		put("make-up","makeup");
		
		put("makeup kit","makeup kit");
		put("make up kit","makeup kit");
		put("make-up kit","makeup kit");
		
		put("facial","face care");
		put(" facial","facial");
		
		put("face","face care");
		put("face pack","face care");
		put("facepack","face care");
		put("face-pack","face care");
		
		put("hair","hair care");
		put("body","body care");
		put("skin","skin care");
		
		put(" oil","oil");
		put(" spa","spa");
		put("wellness","wellness");
		
		put("kohl","kohl");
		put("mascara","mascara");
		put("kajal","kajal");
		put("eye","eye care");
		put("eyeshadow","eye shadow");
		put("eye shadow","eye shadow");
		put("eye-shadow","eye shadow");
		put("eyepencil","eye pencil");
		put("eye pencil","eye pencil");
		put("eye-pencil","eye pencil");
		put("eyeliner","eyeliner");
		put("eye liner","eyeliner");
		put("eye-liner","eyeliner");
		
		put("nail","nail care");
		put("nail art","nail art");
		put("nailart","nail art");
		put("nail-art","nail art");
		put("nailpolish","nail polish");
		put("nail-polish","nail polish");
		put("nail polish","nail polish");
		put("nailenamel","nail enamel");
		put("nail enamel","nail enamel");
		put("nail-enamel","nail enamel");
		put("nailcolor","nail color");
		put("nail color","nail color");
		put("nail-color","nail color");
		
		put("lip","lip care");
		put("lip balm","lip care");
		put("lipbalm","lip care");
		put("lip-balm","lip care");
		put("lipstick","lipstick");
		put("lip stick","lipstick");
		put("lip-stick","lipstick");
		put("lippencil","lip pencil");
		put("lip-pencil","lip pencil");
		put("lip pencil","lip pencil");
		put("lipliner","lip liner");
		put("lip liner","lip liner");
		put("lip-liner","lip liner");
	}};
	
	public static final Map<String, String > masterCatsInt = new HashMap<String, String>(){{
		put("babydoll","babydoll");
		put("baby doll","babydoll");
		put("baby-doll","babydoll");
		
		put("swimsuit","swimwear");
		put("swim suit","swimwear");
		put("swim-suit","swimwear");
		put("swimwear","swimwear");
		put("swim wear","swimwear");
		put("swim-wear","swimwear");
		
		put("bath robe","bath robe");
		put("bathrobe","bath robe");
		put("bath-robe","bath robe");
		
		put("cover up","cover up");
        put("coverup","cover up");
        put("cover-up","cover up");
        
		put("beach","beachwear");
		put("costume","costume");
		
		put("lingerie","lingerie");
		put("monokini","monokini");
		put("bikni","bikini");
		put("bikini","bikini");
		put("corset","corset");
		
		put("garter belt","garter belt");
		put("garterbelt","garter belt");
		put("garter-belt","garter belt");
		
		put("stocking","stockings");
		
		put("teddy","teddy");
		put("hipster","hipster");
		put("shorts","shorts");
		put("thong","thong");
		put("dress","dress");
		put("chemise","chemise");
		put("panty","panty");
		put("panties","panty");
		put("camisole","camisole");
		
		put("tights","tights");
		
		put("sarong","sarong");
		
		put(" nighty","nighty");
		put("nighty","nightwear");
		put("nightwear","nightwear");
		put("night wear","nightwear");
		put("night-wear","nightwear");
		
		put("halter","halter");
		
		put("bra","bra");
		put("non padded bra","non padded bra");
		put("nonpadded bra","non padded bra");
		put("non-padded bra","non padded bra");
		
		put("non wired bra","non wired bra");
		put("nonwired bra","non wired bra");
		put("non-wired bra","non wired bra");
		
		put("t shirt bra","t shirt bra");
		put("tshirt bra","t shirt bra");
		put("t-shirt bra","t shirt bra");
		
		put("strapless bra","strapless bra");
		put("padded bra","padded bra");
		put("wired bra","wired bra");
		
		put("bridal bra","bridal bra");
		put("bridal-bra","bridal bra");
		
		put("underwire bra","underwire bra");
		put("underwire-bra","underwire bra");
		put("underwirebra","underwire bra");
		put("underwire","underwire bra");
		put("underwired bra","underwire bra");
		put("underwired-bra","underwire bra");
		put("underwiredbra","underwire bra");
		
		put(" slip","slip");
		put(" robe","robe");
	}};
	
	public static final Map<String, String > masterCatsAnC = new HashMap<String, String>(){{
		put("painting","painting");
		put("handicraft","handicraft");
		put("statue","statue");
		put("decor","decor");
		
		put("dryfruit","dry fruits");
		put("dry-fruit","dry fruits");
		put("dry fruit","dry fruits");
		put("lakshmi","lakshmi");
		put("laxmi","lakshmi");
		put("ganesh","ganesha");
		put("buddha","buddha");
		put("durga","durga");
		
		put("vase","vase");
		
		put("tribal","tribal-art");
		
		put("wall art","wall art");
		put("wallart","wall art");
		put("wall-art","wall art");
		
		put("art print","art print");
		put("artprint","art print");
		put("art-print","art print");
	}};
	
	public static final Map<String, String > masterCatsLifestyle = new HashMap<String, String>(){{
	    
		put("ashtray","ashtray");
		put("ash tray","ashtray");
		put("ash-tray","ashtray");
		
		put("entertainment unit","entertainment unit");
		put("tv unit","tv unit");
		put("tv cabinet","tv cabinet");
		put(" couch","couch");
		put(" bench","bench");
		
		put("sun lounger","sun lounger");
		put("sunlounger","sun lounger");
		put("sun-lounger","sun lounger");
		
		put("bookshelf","book shelf");
		put("book shelf","book shelf");
		put("book-shelf","book shelf");
		
		put("bookcase","book rack");
		put("book case","book rack");
		put("book-case","book rack");
		
		put("bookrack","book rack");
		put("book rack","book rack");
		put("book-rack","book rack");
		
		put(" tray","tray");
		
		put(" bowl","bowl");
		
		put("hookah","hookah");
		
		put(" bar ","bar");
		put("wine","bar");
		
		put("display unit","display unit");
		
		put("tv cabinet","tv cabinet");
		
		put("bar cabinet","bar cabinet");
		put("bar set","bar set");
		put("bar stool","bar stool");
		put("bar chair","bar chair");
		put("bar trolley","bar trolley");
		
		put("wine rack","wine rack");
		put("winerack","wine rack");
		put("wine-rack","wine rack");
		
		put("futon","futon");
		put("pouffe","pouffe");
		put("recliner","recliner");
		
		put("drawer","drawer");
		put("sideboard","sideboard");
		put("side board","sideboard");
		put("side-board","sideboard");
		
		put("bedroom set","bedroom set");
		put("bed room set","bedroom set");
		put("bed-room set","bedroom set");
		
		put("bedside table","bedside tables");
		put("bed side table","bedside tables");
		put("bed-side table","bedside tables");
		
		put("mirror","mirror");
		
		put("dining","dining");
		put("dining set","dining set");
		put("dining table","dining tables");
		
		put(" bed "," bed");
		put("wooden bed","wooden bed");
		put("king size bed","king size bed");
		put("kingsize bed","king size bed");
		put("king-size bed","king size bed");
		put("queen size bed","queen size bed");
		put("queensize bed","queen size bed");
		put("queen-size bed","queen size bed");
		
		put("sofa","sofa");
		put("ottoman","ottoman");
		
		put("sofa","sofa");
		put("single seater sofa","single seater sofa");
		put("single-seater-sofa","single seater sofa");
		
		put("sofa bed","sofa bed");
		put("sofa-bed","sofa bed");
		
		put("wooden sofa","wooden sofa");
		put("wooden-sofa","wooden sofa");
		
		put("two seater sofa","two seater sofa");
		put("two-seater-sofa","two seater sofa");
		put("double seater sofa","two seater sofa");
		put("double-seater-sofa","two seater sofa");
		
		put("three seater sofa","three seater sofa");
		put("three-seater-sofa","three seater sofa");
		put("triple seater sofa","three seater sofa");
		put("triple-seater-sofa","three seater sofa");
		
		put("sofa set","sofa set");
		put("sofaset","sofa set");
		put("sofa-set","sofa set");
		
		put("mithai","sweets");
		put("sweets","sweets");
		put("pooja","pooja");
		put("diwali","diwali");
		put("diya","diya");
		put("pooja","pooja");
		put("thali","thali");
		put("cracker","crackers");
		put("handicraft","handicraft");
		
		put("chocolate","chocolates");
		
		put("combo","gift combo");
		put("hamper","gift hamper");
		put("chair","chair");
		put("magnet","magnet");
		
		put("candle holder","candle holder");
		put("candleholder","candle holder");
		put("candle-holder","candle holder");
		
		put("rangoli","rangoli");
		
		put("flowers","flower");
		put("bouquet","bouquet");
		
		put("wine","bar");
		
		put(" rug","rug");
		
		put("cutlery","cutlery");
		put("vase","vase");
		
		put("wall shelf","wall shelf");
		put("wallshelf","wall shelf");
		put("wall-shelf","wall shelf");
		
		put("wall shelve","wall shelf");
		put("wallshelve","wall shelf");
		put("wall-shelve","wall shelf");
		
		put("clock","clock");
		
		put("wall clock","wall clock");
		put("wallclock","wall clock");
		put("wall-clock","wall clock");
		
		put("painting","painting");
		
		put("cushion","cushion");
		
		put("cushion cover","cushion cover");
		put("cushioncover","cushion cover");
		put("cushion-cover","cushion cover");
		
		put("shot-glass","shot glass");
		put("shotglass","shot glass");
		put("shot glass","shot glass");
		
		put("wall-art","wall art");
		put("wall art","wall art");
		put("wallart","wall art");
		
		put("coaster","coaster");
		put("poster","poster");
		
		put("dryfruit","dry fruits");
		put("dry-fruit","dry fruits");
		put("dry fruit","dry fruits");
		put("lakshmi","lakshmi");
		put("laxmi","lakshmi");
		put("ganesh","ganesha");
		put("buddha","buddha");
		put("durga","durga");
		
		put("candle","candle");
		put("lamp","lamps");
		put("lampshade","lampshade");
		
		put("photo frame","photo frame");
		put("photoframe","photo frame");
		put("photo-frame","photo frame");
		
		put("decal","decal");
		put("diwan","diwan");
		put("carpet","carpet");
		put("curtain","curtains");
		
		put("doormat","doormat");
		put("door mat","doormat");
		put("door-mat","doormat");
		
		put("bean bag","bean bag");
		put("beanbag","bean bag");
		put("bean-bag","bean bag");
		
		put(" decor","decor");
		put("statue","statue");
		put(" table","tables");
		
		put("bedsheet","bedsheet");
		put("bed sheet","bedsheet");
		put("bed-sheet","bedsheet");
		
		put("coffee table","coffee table");
		put("coffee-table","coffee table");
		put("coffeetable","coffee table");
		
		put("console table","console table");
		put("console-table","console table");
		put("consoletable","console table");
		
		put("balcony table","balcony table");
		put("balcony-table","balcony table");
		put("balconytable","balcony table");
		
		put(" side table","side table");
		put(" side-table","side table");
		put(" sidetable","side table");
		
		put("cushion cover","cushion cover");
		put("cushioncover","cushion cover");
		
		put("notebook","notebook");
		put("note book","notebook");
		
		put("luggage tag","luggage tag");
		
		put("coffee mug","coffee mug");
		put("coffeemug","coffee mug");
		put("coffee-mug","coffee mug");
		
		put(" mug","mug");
		
		put("keychain","keychain");
		put("key chain","keychain");
		put("key-chain","keychain");
        
    }};
    
	public static final Map<String, String > masterCatsFashionM = new HashMap<String, String>(){{
       
		/**
		 * T shirt can get classified as shirt, we need to get that right.
		 * Always keep t shirt above, so that it is used, then only shirt is used 
		 */
		
		put("sherwani","sherwani");
		put("shervani","sherwani");
		put("dupatta","dupatta");
		put(" safa","safa");
		
		put("juti","juti");
		put("jooti","juti");
		
		put("fusion wear","indo western");
		put("indo western","indo western");
		put("indowestern","indo western");
		put("indo-western","indo western");
		
		put(" polo","t shirt");
		put("polo","polo t shirt");
		
		put(" henley","t shirt");
		put("henley","henley");
		
		put("stripe t shirt","stripe t shirt");
		put("stripe tshirt","stripe t shirt");
		put("stripe t-shirt","stripe t shirt");
		put("striped t shirt","stripe t shirt");
		put("striped tshirt","stripe t shirt");
		put("striped t-shirt","stripe t shirt");
		put("stripe tee","stripe t shirt");
		put("striped tee","stripe t shirt");
		
		put("solid t shirt","solid t shirt");
		put("solid tshirt","solid t shirt");
		put("solid t-shirt","solid t shirt");
		put("solid tee","solid t shirt");
		put("solid tee","solid t shirt");
		
		put("slim fit t shirt","slim fit t shirt");
		put("slim fit tshirt","slim fit t shirt");
		put("slim fit t-shirt","slim fit t shirt");
		put("slim fit tee","slim fit t shirt");
		put("slim fit tee","slim fit t shirt");
		
		put(" t.shirt","t shirt");
		put(" t shirt","t shirt");
        put(" tshirt","t shirt");
        put(" t-shirt","t shirt");
        put(" tee ","t shirt");
        
        put("sweatshirt","sweatshirt");
        put("sweat shirt","sweatshirt");
        put("sweat-shirt","sweatshirt");
        
        put("boxer","boxer shorts");
        put(" boxer short","boxer shorts");
        put(" shorts","shorts");
        
        put(" suit","suit");
        put("kurta","kurta");
        
        put(" tie","tie");
        
        
        put("pyjama","pyjamas");
        put("pajama","pyjamas");
        put("trouser","trouser");
        put("chino","chinos");
        put("jeans","jeans");
        put("blazer","blazer");
        put("sweater","sweater");
        put("cardigan","sweater");
        put("pullover","sweater");
        put("jacket","jacket");
        put("coat","jacket");
        put("zipper","jacket");
        put("blazer","blazer");
        put("hoodie","hoodie");
        
        put("waistcoat","waistcoat");
        put("waist coat","waistcoat");
        
        //put(" track","track pants");
        put("track-pant","track pants");
        put("track pant","track pants");
        put("trackpant","track pants");
        
        put("sweat-pant","sweat pants");
        put("sweat pant","sweat pants");
        put("sweatpant","sweat pants");
        
        put("bermuda","bermuda");
        
        put(" cargo","cargo pants");
        put("cargo-pant","cargo pants");
        put("cargo pant","cargo pants");
        put("cargopant","cargo pants");
        
        put("lace-up","shoes");
        put("lace up","shoes");
        put("laceup","shoes");
        put("slip-on","shoes");
        put("slip on","shoes");
        put("slipon","shoes");
        put("shoe","shoes");
        put("loafer","loafer");
        put("mocassin","mocassin");
        put("boatshoes","boat shoes");
        put("boat shoes","boat shoes");
        put("boat-shoes","boat shoes");
        put("boot","boots");
        put("brogue","brogue");
        put("sneaker","sneakers");
        put("sandal","sandals");
        
        put("chappal","slippers");
        put("slipper","slippers");
        
        put("flipflop","flip flops");
        put("flip flop","flip flops");
        put("flip-flop","flip flops");
        
        put(" shirt","shirt");
        put("slim fit shirt","slim fit shirt");
        put("slim-fit shirt","slim fit shirt");
        put("slim-fit-shirt","slim fit shirt");
        put("slim shirt","slim fit shirt");
        put("slim-shirt","slim fit shirt");
        put("solid shirt","solid shirt");
        put("solid-shirt","solid shirt");
        put("solidshirt","solid shirt");
        put("check shirt","check shirt");
        put("check-shirt","check shirt");
        put("checkshirt","check shirt");
        put("checked shirt","check shirt");
        put("checked-shirt","check shirt");
        put("checkedshirt","check shirt");
        put("print shirt","print shirt");
        put("print-shirt","print shirt");
        put("printshirt","print shirt");
        put("printed shirt","print shirt");
        put("printed-shirt","print shirt");
        put("printedshirt","print shirt");
        put("stripe shirt","stripe shirt");
        put("stripe-shirt","stripe shirt");
        put("stripeshirt","stripe shirt");
        put("striped shirt","stripe shirt");
        put("striped-shirt","stripe shirt");
        put("stripedshirt","stripe shirt");
    }};
    
    public static final Map<String, String > masterCatsAccessoriesM = new HashMap<String, String>(){{
    	
    	put("keychain","keychain");
    	put("key chain","keychain");
    	put("key-chain","keychain");
    	
    	put("edp","perfume");
		put("edt","perfume");
		put("perfume","perfume");
		put("parfum","perfume");
		put("toilette","perfume");
		put("fragrance","perfume");
		put("cologne","perfume");
		
    	put(" tie","tie");
    	put("bowtie","bow tie");
    	put("bow tie","bow tie");
    	put("bow-tie","bow tie");
    	put("necktie","neck tie");
    	put("neck tie","neck tie");
    	put("neck-tie","neck tie");
    	
    	put("lighter","lighter");
        put(" cap","cap");
        
        put("shoes","shoes");
        
        put("aviator","sunglasses");
        put("wayfarer","sunglasses");
        put("peepers","sunglasses");
        put("shades","sunglasses");
        put("sunglass","sunglasses");
        put("sun glass","sunglasses");
        put("sun-glass","sunglasses");
        
        put(" belt","belt");
        put("wallet","wallet");
        
        put("watch","watch");
        
        put(" bag","bags");
        put("backpack","backpack");
        put("back pack","backpack");
        put("back-pack","backpack");
        
        put("slingbag","slingbag");
        put("sling bag","slingbag");
        put("sling-bag","slingbag");
        
        put("shaving-bag","shaving bag");
        put("shavingbag","shaving bag");
        put("shaving bag","shaving bag");
        
        put("shaving-kit","shaving kit");
        put("shavingkit","shaving kit");
        put("shaving kit","shaving kit");
        
        put("helmet","helmet");
        
        put(" pen","pen");
        
        put("card holder","card holder");
        put("cardholder","card holder");
        put("card-holder","card holder");
        
        put("cufflink","cufflinks");
        put("cuff link","cufflinks");
        put("cuff-link","cufflinks");
        
        put("bracelet","bracelet");
    }};
    
	public static final Map<String, String > masterCatsFashionW = new HashMap<String, String>(){{
		
		put("clutch","clutch");
		
		put(" cami","camisole");
		put(" belt","belt");
		
		put("sherwani","sherwani");
		put("shervani","sherwani");
		
		put("necklace","necklace");
		
		put("boxer","boxer shorts");
		put(" boxer short","boxer shorts");
        put(" shorts","shorts");
        
        put("plazo","plazo suit");
        put(" plazo","salwar");
        
        put("designer suit","designer suit");
        put("churidar","churidar suit");
        put("chudidar","churidar suit");
        put("anarkali","anarkali suit");
        put("anarka","anarkali suit");
        put("designer suit","salwar");
        //put(" suit","salwar");
        put("salwar","salwar");
        put("salvar","salwar");
        
        put("dupatta","dupatta");
        put("with dupatta","salwar suit with dupatta");
        put("wedding","wedding");
        put("bridal","wedding");
        put("actress","bollywood");
        put("bollywood","bollywood");
        put("replica","bollywood");
        put("lehnga","lehnga");
        put("lehenga","lehnga");
        put("lehanga","lehnga");
        put("saree","saree");
        put(" sari","saree");
        put("bhagalpuri saree","bhagalpuri saree");
        put("bhagalpuri sari","bhagalpuri saree");
        put("banarasi saree","banarasi saree");
        put("banarasi sari","banarasi saree");
        put("vanarasi saree","banarasi saree");
        put("vanarasi sari","banarasi saree");
        put("with blouse","saree with blouse");
        put("kurti","kurti");
        put("kurta","kurti");
        
        
        // dress
        put(" dress","dress");
        put(" dresses","dress");
        
        put("maxidresses","maxi");
        put("maxidress","maxi");
        put("maxi dress","maxi");
        put("maxidresses","maxi dress");
        put("maxidress","maxi dress");
        put("maxi dress","maxi dress");
        put("maxi-dress","maxi");
        
        put("minidresses","mini");
        put("minidress","mini");
        put("mini dress","mini");
        put("minidresses","mini dress");
        put("minidress","mini dress");
        put("mini dress","mini dress");
        put("mini-dress","mini");
        put("peplum","peplum dress");
        put("bodycon","bodycon dress");
        put("body con","bodycon dress");
        
        put("tunic","tunic");
        put(" tops","top");
        put(" top","top");
        put("-top","top");
        put(" tee","t shirt");
        put(" vest","top");
        
        put(" tank","tank top");
        put("tanktop","tank top");
        put("tank top","tank top");
        put("tank-top","tank top");
        
        put("jacquard","jacquard");
        put("dugaree","dungaree");
        put("co ord","co ords");
        put("co-ord","co ords");
        put("coord","co ords");
        
        put("muffler","muffler");
        put("shawl","shawl");
        
        put("blouse","blouse");
        put(" shirt","shirt");
        
        put("blazer","blazer");
        put("hoodie","hoodie");
        put("sweatshirt","sweatshirt");
        put("sweat shirt","sweatshirt");
        put("sweat-shirt","sweatshirt");
        put("jacket","jacket");
        put("shacket","shacket");
        put("coat","coat");
        put("shrug","shrugs");
        put("cover up","cover up");
        put("coverup","cover up");
        put("cover-up","cover up");
        put("skirts","skirt");
        put("skirt","skirt");
        put("palazzo","palazzo");
        put(" pant","pants");
        put("legging","leggings");
        put("jegging","jeggings");
        put("jeans","jeans");
        put("denims","jeans");
        put(" harem","harem pants");
        put(" capri","capris");
        
        put(" tights","tights");
        put("shorts","shorts");
        put("trouser","trouser");
        put("jumpsuit","jumpsuit");
        put("playsuit","playsuit");
        put("dungaree","dungaree");
        put("dugaree","dungaree");
        put("nightwear","nightwear");

        put("pyjama","pyjamas");
        put("pajama","pyjamas");
        
        put(" t shirt","t shirt");
        put(" tshirt","t shirt");
        put(" t-shirt","t shirt");
        put(" polo","t shirt");
        
        put("jersey","jersey");
        
        put("scarf","scarf");
        put("scarve","scarf");
        put("stole","stole");
        
        put("cardigan","sweater");
        put("sweater","sweater");
        put("pullover","sweater");
        
        // shoes
        put(" shoe","shoes");
        put("shoes","shoes");
        put("flats","flats");
        put("stiletto","stiletto");
        put(" flat","flats");
        put("flipflop","flip flops");
        put("flip flop","flip flops");
        put("flip-flop","flip flops");
        put("chappal","chappals");
        put("sandals","sandals");
        put("sandal","sandals");
        put("peeptoe","peep toe");
        put("peep toe","peep toe");
        put("peep-toe","peep toe");
        put("wedge","wedges");
        put("wedges","wedges");
        put("wedges","wedges");
        put("pumps","pumps");
        put("bellies","ballerina");
        put("ballerina","ballerina");
        put("footwear","shoes");
        put("foot wear","shoes");
        put("foot-wear","shoes");
        put("boots","boots");
        put(" boot","boots");
        put(" heels","heels");
        put(" heel","heels");
        put("trainers","trainers");
        put("sneakers","sneakers");
        put("loafer","loafers");
        put("mocassin","mocassins");
        put("canvas shoe","canvas shoes");
        put("canvas-shoe","canvas shoes");
        
    }};
    
    public static final Map<String, String > masterCatsAccessoriesW = new HashMap<String, String>(){{
        
    	put("edp","perfume");
		put("edt","perfume");
		put("perfume","perfume");
		put("parfum","perfume");
		put("toilette","perfume");
		put("fragrance","perfume");
		put("cologne","perfume");
		
    	put("nexus","nexus stuff");
    	
    	put("nexus 4 case","nexus 4 cover");
    	put("nexus 5 case","nexus 5 cover");
    	
    	put("samsung","samsung stuff");
    	
    	put("samsung s3 case","samsung s3 cover");
    	put("samsung s4 case","samsung s4 cover");
    	put("samsung s5 case","samsung s5 cover");
    	
    	put("moto x case","moto x cover");
    	put("moto-x case","moto x cover");
    	
    	put("laptop sleeve","laptop cover");
    	
    	put(" ipad","ipad stuff");
    	put("ipad sleeve","ipad cover");
    	
    	put("iphone 4","iphone stuff");
    	put("iphone 4/4s case","iphone 44s cover");
    	put("iphone 5","iphone stuff");
    	put("iphone 5/5s case","iphone 55s cover");
    	
    	put(" charm","charm");
    	
    	put("eyewear case","eyewear case");
    	put("eyewear-case","eyewear case");
		
		put(" bag ","bags");
        put("bags","bags");
        put("potli","potli bags");
        put("handbag","handbag");
        put("hand bag","handbag");
        put("slingbag","slingbag");
        put("sling bag","slingbag");
        put("sling-bag","slingbag");
        put("clutch","clutch");
        put("pouch","pouch");
        put("backpack","backpack");
        put("back pack","backpack");
        put("back-pack","backpack");
        put("card holder","card holder");
        put("cardholder","card holder");
        put("card-holder","card holder");
        put("passport holder","passport holder");
        put("passportholder","passport holder");
        put("passport-holder","passport holder");
        put("shopping bag","shopping bag");
        put("shopping-bag","shopping bag");
        put("shoppingbag","shopping bag");
        put("purse","purse");
        put("wallet","wallet");
        put("satchel","satchel");
        put(" tote ","tote bag");
        put("wristlet","wristlet");
        put("cross bag","cross bag");
        put("crossbag","cross bag");
        put("cross-bag","cross bag");
        put("cross body bag","cross bag");
        put("crossbody bag","cross bag");
        put("cross-body bag","cross bag");
        
        put("jhumka","earring");
        put("jhumki","earring");
        put(" earrings","earring");
        put(" earring","earring");
        put(" earing","earring");
        put("earcuff","earcuff");
        put("ear cuff","earcuff");
        put("ear-cuff","earcuff");
        put("studs","studs");
        
        put("jewellery","jewellery");
        put("bangle","bangle");
        put("bracelet","bracelet");
        put("hand cuff","hand cuff");
        put("handcuff","hand cuff");
        put("hand-cuff","hand cuff");
        put("hoops","hoops");
        put("necklace","necklace");
        put("necklce","necklace");
        put("neckwear","necklace");
        put("neck wear","necklace");
        put("neck-wear","necklace");
        put("neck piece","necklace");
        put("neckpiece","necklace");
        put("collar pin","collar pin");
        put("collarpin","collar pin");
        put("brooch","brooch");
        put("anklet","anklet");
        put("hair band","hairband");
        put("hair-band","hairband");
        put("hairband","hairband");
        
        put("pendant","pendant");
        put("pendent","pendant");
        put(" ring ","ring");
        put(" rings ","ring");
        put("nosepin","nosepin");
        put("nose pin","nosepin");
        put("nose ring","nosepin");
        put("nosering","nosepin");
        
        put("belt","belt");
        
        put("socks","socks");
        
        put("watches","watch");
        put("watch","watch");
        
        put("shades","sunglasses");
        put("sunglass","sunglasses");
        put("sun glass","sunglasses");
        put("sun-glass","sunglasses");
    }};
	
    public static final Map<String, String > masterCatsJewellery = new HashMap<String, String>(){{
        
    	put("studs","studs");
    	put("jhumka","earring");
        put("jhumki","earring");
        put(" earrings","earring");
        put(" earring","earring");
        put(" earing","earring");
        put("jewellery","jewellery");
        put("pendant","pendant");
        put("pendent","pendant");
        put(" band","ring");
        put(" ring ","ring");
        put(" rings ","ring");
        put("nosepin","nosepin");
        put("nose pin","nosepin");
        put("nose ring","nosepin");
        put("nosering","nosepin");
        
        put("bangle","bangle");
        put("bracelet","bracelet");
        put("hand cuff","hand cuff");
        put("handcuff","hand cuff");
        put("hand-cuff","hand cuff");
        put("hoops","hoops");
        put("necklace","necklace");
        put("necklce","necklace");
        put("neck piece","necklace");
        put("neckpiece","necklace");
        put("collar pin","collar pin");
        put("collarpin","collar pin");
        put("brooch","brooch");
        put("anklet","anklet");
        
        
    }};
    
	public static boolean isCelebrityDress(String text){
		
		for (String masterCelebrity : masterCelebrities) {
			if(text.contains(masterCelebrity))
				return true;
		}
		
		return false;
	}

	public static List<String> getColorsFromFreeFlowingText(String text){
		
		Set<String> colors = new TreeSet<String>();
		
		text = text.toLowerCase();
		
		for (String masterColor : masterColors) {
			if(text.contains(masterColor))
				colors.add(masterColor);
		}
		
		return new ArrayList<String>(colors);
	}
	
	public static List<String> getCategoryFromFreeFlowingText(String text, String collName){
		
		Set<String> cats = new TreeSet<String>();
		
		text = " " + text.toLowerCase() + " ";
		
		Map<String, String> masterCats = new HashMap<String, String>();
		
		if(collName.equalsIgnoreCase("Fashion-W")){
			masterCats.putAll(masterCatsFashionW);
			masterCats.putAll(masterCatsAccessoriesW);
		}
		else if(collName.equalsIgnoreCase("Accessories-W"))
				masterCats = masterCatsAccessoriesW;
		else if(collName.equalsIgnoreCase("Jewellery"))
			masterCats = masterCatsJewellery;
		else if(collName.equalsIgnoreCase("Fashion-M"))
			masterCats = masterCatsFashionM;
		else if(collName.equalsIgnoreCase("Accessories-M"))
			masterCats = masterCatsAccessoriesM;
		else if(collName.equalsIgnoreCase("Lifestyle"))
			masterCats = masterCatsLifestyle;
		else if(collName.equalsIgnoreCase("Mishmash"))
			masterCats = masterCatsLifestyle;
		else if(collName.equalsIgnoreCase("Music n Movies"))
			masterCats = masterCatsMnM;
		else if(collName.equalsIgnoreCase("Art n Craft"))
			masterCats = masterCatsAnC;
		else if(collName.equalsIgnoreCase("Intimacy"))
			masterCats = masterCatsInt;
		else if(collName.equalsIgnoreCase("Beauty n Health"))
			masterCats = masterCatsBnH;
		else if(collName.equalsIgnoreCase("Sports"))
			masterCats = masterCatsSports;
		else if(collName.equalsIgnoreCase("Electronics"))
			masterCats = masterCatsElectronics;
		else if(collName.equalsIgnoreCase("Kids"))
			masterCats = masterCatsKids;
		else if(collName.equalsIgnoreCase("Mum n Me"))
			masterCats = masterCatsKids;
		
		for (String masterCat : masterCats.keySet()) {
			if(text.contains(masterCat)){
				cats.add(masterCats.get(masterCat));
			}
		}
		
		if(isCelebrityDress(text))
			cats.add("bollywood");
		
		if(cats.contains("t shirt") && cats.contains("shirt"))
			cats.remove("shirt");
		
		if(cats.contains("tray") && cats.contains("ashtray"))
			cats.remove("tray");
		
		return new ArrayList<String>(cats);
	}

	public static List<String> getStylesFromFreeFlowingText(String text){
		
		Set<String> styles = new TreeSet<String>();
		
		text = text.toLowerCase();
		
		for (String masterStyle : masterStyles.keySet()) {
			if(text.contains(masterStyle))
				styles.add(masterStyles.get(masterStyle));
		}
		
		if(isCelebrityDress(text))
			styles.add("bollywood");
		
		return new ArrayList<String>(styles);
	}
	
	public static List<String> getPersonalCareBrandsFromFreeFlowingText(String text){
		
		Set<String> brands = new TreeSet<String>();
		
		text = text.toLowerCase().replaceAll("'", " ");
		text = text.toLowerCase().replaceAll("&", "and");
		
		for (String masterPersonalCareBrand : masterPersonalCareBrands) {
			if(text.contains(masterPersonalCareBrand))
				brands.add(masterPersonalCareBrand);
		}
		
		return new ArrayList<String>(brands);
	}
}
