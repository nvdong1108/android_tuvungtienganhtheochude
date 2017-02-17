package com.nvd.data;

import java.util.ArrayList;

import com.nvd.item.vocabulary;
import com.nvd.vocabulary.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class dataSQLite extends SQLiteOpenHelper {
	Context context = null;
	private static final String name = "data.sqlite";
	private static final String path = "/data/data/com.nvd.vocabulary/databases/";
	SQLiteDatabase database = null;

	public dataSQLite(Context context) {
		super(context, "data.sqlite", null, 1);
		this.context = context;
	}

	public Cursor getData(String sql) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.rawQuery(sql, null);
		return c;

	}

	public void opendatabase() {
		String urn = path + name;
		database = SQLiteDatabase.openDatabase(urn, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
	}

	public void QueryData(String sql) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);

	}

	public int GET_SIZE_LIST() {
		ArrayList<vocabulary> list = new ArrayList<vocabulary>();
		Cursor c = null;
		c = database
				.query("family", null, "yeuthich=1", null, null, null, null);
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5), c
							.getString(6)));
		}
		c = database.query("job", null, "yeuthich=1", null, null, null, null);
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5), c
							.getString(6)));
		}
		c = database.query("sport", null, "yeuthich=1", null, null, null, null);
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5), c
							.getString(6)));
		}
		c = database.query("bedroom", null, "yeuthich=1", null, null, null,
				null);
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5), c
							.getString(6)));
		}
		c = database.query("animals", null, "yeuthich=1", null, null, null,
				null);
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5), c
							.getString(6)));
		}
		c.close();
		database.close();
		return list.size();
	}

	public ArrayList<vocabulary> SELECT_TABLE(String tb) {
		ArrayList<vocabulary> list = new ArrayList<vocabulary>();
		Cursor c = null;
		if (tb.equals("yt")) {
			c = database.query("family", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("job", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("sport", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("bedroom", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("animals", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
		} else if (tb.equals("house")) {
			c = database.query("kitchen", null, null, null, null, null, null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("bedroom", null, null, null, null, null, null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("utilitytool", null, null, null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("bathroom", null, null, null, null, null, null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
		} else {
			c = database.query(tb, null, null, null, null, null, null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
		}

		c.close();
		database.close();
		return list;
	}

	public int SELECT_ID(String vn, String tb) {
		int id = 0;
		Cursor c = null;
		c = database.query(tb, null, "vn='" + vn + "'", null, null, null, null);
		id = c.getInt(1);
		c.moveToNext();
		c.close();
		return id;
	}

	public void UpdateVocabulary(String W_ENG, int YEUTHICH) {
		String sql = "UPDATE tuvung SET YEUTHICH = " + YEUTHICH
				+ " WHERE W_ENG = '" + W_ENG + "'";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);

	}

	public void UPLOAD_YEUTHICH(String tb, int id, int yeuthich) {
		String sql = "UPDATE " + tb + " SET yeuthich = " + yeuthich
				+ " WHERE id = " + id + "";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}

	//
	public void UPLOAD_V_YEUTHICH(String eng, int id, int yeuthich) {
		String sql = "UPDATE family SET yeuthich = " + yeuthich
				+ " WHERE id = " + id + " and eng = '" + eng + "'";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}

	//
	public void UPLOAD_IMG(String tb, int id, int img) {
		String sql = "UPDATE " + tb + " SET hinhanh = " + img + " WHERE id = "
				+ id + "";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}

	//
	public void UPLOAD_ALL_IMG() {
		UPLOAD_IMG("job", 1, R.drawable.accountant);
		UPLOAD_IMG("job", 2, R.drawable.baker);
		UPLOAD_IMG("job", 3, R.drawable.barber);
		UPLOAD_IMG("job", 4, R.drawable.barman);
		UPLOAD_IMG("job", 5, R.drawable.builder);
		//
		UPLOAD_IMG("job", 6, R.drawable.butcher);
		UPLOAD_IMG("job", 7, R.drawable.carpenter);
		UPLOAD_IMG("job", 8, R.drawable.carpenter);
		UPLOAD_IMG("job", 9, R.drawable.chambermaid);
		UPLOAD_IMG("job", 10, R.drawable.chef);
		//
		UPLOAD_IMG("job", 11, R.drawable.cleaner);
		UPLOAD_IMG("job", 12, R.drawable.dentist);
		UPLOAD_IMG("job", 13, R.drawable.photographer);
		UPLOAD_IMG("job", 14, R.drawable.doctor);
		UPLOAD_IMG("job", 15, R.drawable.electrician);
		//
		UPLOAD_IMG("job", 16, R.drawable.engineer);
		UPLOAD_IMG("job", 17, R.drawable.fireman);
		UPLOAD_IMG("job", 18, R.drawable.fishmonger);
		UPLOAD_IMG("job", 19, R.drawable.flight_attendant);
		UPLOAD_IMG("job", 20, R.drawable.hairdresser);
		//
		UPLOAD_IMG("job", 21, R.drawable.judge);
		UPLOAD_IMG("job", 22, R.drawable.lawyer);
		UPLOAD_IMG("job", 23, R.drawable.nurse);
		UPLOAD_IMG("job", 24, R.drawable.optician);
		UPLOAD_IMG("job", 25, R.drawable.painter);
		//
		UPLOAD_IMG("job", 26, R.drawable.plumber);
		UPLOAD_IMG("job", 27, R.drawable.policeman);
		UPLOAD_IMG("job", 28, R.drawable.porter);
		UPLOAD_IMG("job", 29, R.drawable.receptionist);
		UPLOAD_IMG("job", 30, R.drawable.reporter);
		//
		UPLOAD_IMG("job", 31, R.drawable.sales_assistant);
		UPLOAD_IMG("job", 32, R.drawable.sales_representative);
		UPLOAD_IMG("job", 33, R.drawable.scientist);
		UPLOAD_IMG("job", 34, R.drawable.secretary);
		UPLOAD_IMG("job", 35, R.drawable.surgeon);
		//
		UPLOAD_IMG("job", 36, R.drawable.tailor);
		UPLOAD_IMG("job", 37, R.drawable.teacher);
		UPLOAD_IMG("job", 38, R.drawable.technician);
		UPLOAD_IMG("job", 39, R.drawable.vet);
		UPLOAD_IMG("job", 40, R.drawable.waiter);
		//
		UPLOAD_IMG("job", 41, R.drawable.welder);
		//
		// table family
		UPLOAD_IMG("family", 1, R.drawable.family);
		UPLOAD_IMG("family", 2, R.drawable.ancestor);
		UPLOAD_IMG("family", 3, R.drawable.great_grandparent);
		UPLOAD_IMG("family", 4, R.drawable.great_grandfather);
		UPLOAD_IMG("family", 5, R.drawable.great_grandmother);
		//
		UPLOAD_IMG("family", 6, R.drawable.grandfather);
		UPLOAD_IMG("family", 7, R.drawable.grandmother);
		UPLOAD_IMG("family", 8, R.drawable.great_uncle);
		UPLOAD_IMG("family", 9, R.drawable.great_aunt);
		UPLOAD_IMG("family", 10, R.drawable.parent);
		//
		UPLOAD_IMG("family", 11, R.drawable.grandparent);
		UPLOAD_IMG("family", 12, R.drawable.father);
		UPLOAD_IMG("family", 13, R.drawable.mother);
		UPLOAD_IMG("family", 14, R.drawable.uncle);
		UPLOAD_IMG("family", 15, R.drawable.uncle);
		//
		UPLOAD_IMG("family", 16, R.drawable.aunt);
		UPLOAD_IMG("family", 17, R.drawable.cousin);
		UPLOAD_IMG("family", 18, R.drawable.sister);
		UPLOAD_IMG("family", 19, R.drawable.brother);
		UPLOAD_IMG("family", 20, R.drawable.sister_in_law);
		//
		UPLOAD_IMG("family", 21, R.drawable.brother_in_law);
		UPLOAD_IMG("family", 22, R.drawable.mother_in_law);
		UPLOAD_IMG("family", 23, R.drawable.father_in_law);
		UPLOAD_IMG("family", 24, R.drawable.son);
		UPLOAD_IMG("family", 25, R.drawable.daughter);
		//
		UPLOAD_IMG("family", 26, R.drawable.nephew);
		UPLOAD_IMG("family", 27, R.drawable.niece);
		UPLOAD_IMG("family", 28, R.drawable.grandson);
		UPLOAD_IMG("family", 29, R.drawable.granddaughter);
		UPLOAD_IMG("family", 30, R.drawable.godfather);
		//
		UPLOAD_IMG("family", 31, R.drawable.adopted_child);
		UPLOAD_IMG("family", 32, R.drawable.half_sister);
		UPLOAD_IMG("family", 33, R.drawable.half_brother);
		UPLOAD_IMG("family", 34, R.drawable.step_father);
		UPLOAD_IMG("family", 35, R.drawable.step_mother);
		//
		UPLOAD_IMG("family", 36, R.drawable.fosterling);
		UPLOAD_IMG("family", 37, R.drawable.orphan);
		/*
		 * table animals
		 */
		UPLOAD_IMG("animals2", 1, R.drawable.zebra);
		UPLOAD_IMG("animals2", 2, R.drawable.giraffe);
		UPLOAD_IMG("animals2", 3, R.drawable.rhinoceros);
		UPLOAD_IMG("animals2", 4, R.drawable.elephant);
		UPLOAD_IMG("animals2", 5, R.drawable.lion);
		//
		UPLOAD_IMG("animals2", 6, R.drawable.lioness);
		UPLOAD_IMG("animals2", 7, R.drawable.cheetah);
		UPLOAD_IMG("animals2", 8, R.drawable.leopard);
		UPLOAD_IMG("animals2", 9, R.drawable.hyena);
		UPLOAD_IMG("animals2", 10, R.drawable.hippopotamus);
		//
		UPLOAD_IMG("animals2", 11, R.drawable.camel);
		UPLOAD_IMG("animals2", 12, R.drawable.monkey);
		UPLOAD_IMG("animals2", 13, R.drawable.chimpanzee);
		UPLOAD_IMG("animals2", 14, R.drawable.gnu);
		UPLOAD_IMG("animals2", 15, R.drawable.gorilla);
		//
		UPLOAD_IMG("animals2", 16, R.drawable.baboon);
		UPLOAD_IMG("animals2", 17, R.drawable.antelope);
		UPLOAD_IMG("animals2", 18, R.drawable.gazelle);
		UPLOAD_IMG("animals2", 19, R.drawable.pigeon);
		UPLOAD_IMG("animals2", 20, R.drawable.falcon);
		//
		UPLOAD_IMG("animals2", 24, R.drawable.owl);
		UPLOAD_IMG("animals2", 25, R.drawable.falcon);
		UPLOAD_IMG("animals2", 26, R.drawable.dove);
		UPLOAD_IMG("animals2", 26, R.drawable.vulture);
		UPLOAD_IMG("animals2", 28, R.drawable.sparrow);
		//
		UPLOAD_IMG("animals2", 29, R.drawable.crow);
		UPLOAD_IMG("animals2", 30, R.drawable.goose);
		UPLOAD_IMG("animals2", 31, R.drawable.duck);
		UPLOAD_IMG("animals2", 32, R.drawable.turkey);
		UPLOAD_IMG("animals2", 33, R.drawable.penguin);
		//
		UPLOAD_IMG("animals2", 34, R.drawable.woodpecker);
		UPLOAD_IMG("animals2", 35, R.drawable.ostrich);
		UPLOAD_IMG("animals2", 36, R.drawable.parrot);
		UPLOAD_IMG("animals2", 37, R.drawable.peacock);
		UPLOAD_IMG("animals2", 38, R.drawable.swan);
		//
		UPLOAD_IMG("animals2", 39, R.drawable.stork);
		UPLOAD_IMG("animals2", 40, R.drawable.crane);
		UPLOAD_IMG("animals2", 41, R.drawable.heron);
		UPLOAD_IMG("animals2", 42, R.drawable.bull);
		UPLOAD_IMG("animals2", 44, R.drawable.calf);
		//
		UPLOAD_IMG("animals2", 45, R.drawable.chicken);
		
		UPLOAD_IMG("animals2", 47, R.drawable.cow);
		UPLOAD_IMG("animals2", 48, R.drawable.donkey);
		UPLOAD_IMG("animals2", 53, R.drawable.horse);
		UPLOAD_IMG("animals2", 57, R.drawable.sheep);
		//
		UPLOAD_IMG("animals2", 58, R.drawable.sow);
		UPLOAD_IMG("animals2", 59, R.drawable.piglet);
		UPLOAD_IMG("animals2", 60, R.drawable.rooster);
		UPLOAD_IMG("animals2", 64, R.drawable.goat);
		UPLOAD_IMG("animals2", 67, R.drawable.grasshopper);
		//
		UPLOAD_IMG("animals2", 68, R.drawable.cricket);
		UPLOAD_IMG("animals2", 69, R.drawable.scorpion);
		UPLOAD_IMG("animals2", 71, R.drawable.cockroach);
		UPLOAD_IMG("animals2", 70, R.drawable.fly);
		UPLOAD_IMG("animals2", 72, R.drawable.spider);
		//
		UPLOAD_IMG("animals2", 73, R.drawable.ladybug);
		UPLOAD_IMG("animals2", 75, R.drawable.wasp);
		UPLOAD_IMG("animals2", 76, R.drawable.snail);
		UPLOAD_IMG("animals2", 77, R.drawable.worm);
		UPLOAD_IMG("animals2", 78, R.drawable.mosquito);
		//
		UPLOAD_IMG("animals2", 79, R.drawable.parasites);
		UPLOAD_IMG("animals2", 80, R.drawable.flea);
		UPLOAD_IMG("animals2", 81, R.drawable.beetle);
		UPLOAD_IMG("animals2", 82, R.drawable.butterfly);
		UPLOAD_IMG("animals2", 83, R.drawable.caterpillar);
		//
		UPLOAD_IMG("animals2", 84, R.drawable.cocoon);
		UPLOAD_IMG("animals2", 86, R.drawable.dragonfly);
		UPLOAD_IMG("animals2", 87, R.drawable.praying_mantis);
		UPLOAD_IMG("animals2", 89, R.drawable.bee);
		UPLOAD_IMG("animals2", 92, R.drawable.tarantula);
		//

		UPLOAD_IMG("animals2", 93, R.drawable.mouse);
		UPLOAD_IMG("animals2", 94, R.drawable.rat);
		UPLOAD_IMG("animals2", 95, R.drawable.squirrel);
		UPLOAD_IMG("animals2", 97, R.drawable.chipmunk);
		//
		UPLOAD_IMG("animals2", 98, R.drawable.rabbit);
		UPLOAD_IMG("animals2", 99, R.drawable.deer);
		UPLOAD_IMG("animals2", 100, R.drawable.doe);
		
		UPLOAD_IMG("animals2", 104, R.drawable.wolf_howl);
		UPLOAD_IMG("animals2", 105, R.drawable.fox);
		//
		UPLOAD_IMG("animals2", 106, R.drawable.bear);
		UPLOAD_IMG("animals2", 107, R.drawable.tiger);
		UPLOAD_IMG("animals2", 108, R.drawable.boar);
		UPLOAD_IMG("animals2", 109, R.drawable.bat);
		UPLOAD_IMG("animals2", 110, R.drawable.beaver);
		//
		UPLOAD_IMG("animals2", 111, R.drawable.skunk);
		UPLOAD_IMG("animals2", 112, R.drawable.raccoon);
		UPLOAD_IMG("animals2", 113, R.drawable.kangaroo);
		UPLOAD_IMG("animals2", 114, R.drawable.koala_bear);
		UPLOAD_IMG("animals2", 116, R.drawable.porcupine);
		//
		UPLOAD_IMG("animals2", 117, R.drawable.panda);
		UPLOAD_IMG("animals2", 119, R.drawable.mole);
		UPLOAD_IMG("animals2", 120, R.drawable.polar_bear);
		UPLOAD_IMG("animals2", 129, R.drawable.frog);
		UPLOAD_IMG("animals2", 130, R.drawable.tadpole);
		//
		UPLOAD_IMG("animals2", 131, R.drawable.toad);
		UPLOAD_IMG("animals2", 132, R.drawable.snake);
		UPLOAD_IMG("animals2", 133, R.drawable.turtl);
		UPLOAD_IMG("animals2", 134, R.drawable.cobra);
		UPLOAD_IMG("animals2", 135, R.drawable.lizard);
		//
		UPLOAD_IMG("animals2", 136, R.drawable.alligator);
		UPLOAD_IMG("animals2", 137, R.drawable.crocodile);
		UPLOAD_IMG("animals2", 138, R.drawable.dragon);
		UPLOAD_IMG("animals2", 139, R.drawable.dinosaurs);
		UPLOAD_IMG("animals2", 140, R.drawable.chameleon);
		//
		UPLOAD_IMG("animals2", 141, R.drawable.seagull);
		UPLOAD_IMG("animals2", 142, R.drawable.pelican);
		UPLOAD_IMG("animals2", 143, R.drawable.seal);
		UPLOAD_IMG("animals2", 144, R.drawable.walrus);
		UPLOAD_IMG("animals2", 146, R.drawable.fish);
		//
		UPLOAD_IMG("animals2", 148, R.drawable.octopus);
		UPLOAD_IMG("animals2", 149, R.drawable.dolphin);
		UPLOAD_IMG("animals2", 150, R.drawable.squid);
		UPLOAD_IMG("animals2", 151, R.drawable.shark);
		UPLOAD_IMG("animals2", 152, R.drawable.jellyfish);
		//
		UPLOAD_IMG("animals2", 153, R.drawable.sea_horse);
		UPLOAD_IMG("animals2", 154, R.drawable.whale);
		UPLOAD_IMG("animals2", 155, R.drawable.starfish);
		UPLOAD_IMG("animals2", 156, R.drawable.lobster);
		UPLOAD_IMG("animals2", 157, R.drawable.shrimp);
		//
		UPLOAD_IMG("animals2", 158, R.drawable.pearl);
		UPLOAD_IMG("animals2", 159, R.drawable.eel);
		UPLOAD_IMG("animals2", 160, R.drawable.shellfish);
		UPLOAD_IMG("animals2", 161, R.drawable.coral);
		UPLOAD_IMG("animals2", 162, R.drawable.clam);
		//
		UPLOAD_IMG("animals2", 163, R.drawable.crab);
		/*
		 * table sprot
		 */
		UPLOAD_IMG("sport", 1, R.drawable.gymnastics);
		UPLOAD_IMG("sport", 2, R.drawable.tennis);
		UPLOAD_IMG("sport", 3, R.drawable.running);
		UPLOAD_IMG("sport", 4, R.drawable.swimming);
		UPLOAD_IMG("sport", 5, R.drawable.riding);
		//
		UPLOAD_IMG("sport", 6, R.drawable.volleyball);
		UPLOAD_IMG("sport", 7, R.drawable.football);
		UPLOAD_IMG("sport", 8, R.drawable.basketball);
		UPLOAD_IMG("sport", 9, R.drawable.table_tennis);
		UPLOAD_IMG("sport", 10, R.drawable.baseball);
		//
		UPLOAD_IMG("sport", 11, R.drawable.golf);
		UPLOAD_IMG("sport", 12, R.drawable.skateboarding);
		UPLOAD_IMG("sport", 13, R.drawable.windsurfing);
		UPLOAD_IMG("sport", 14, R.drawable.badminton);
		UPLOAD_IMG("sport", 15, R.drawable.ice_skating);
		//
		UPLOAD_IMG("sport", 16, R.drawable.skiing);
		UPLOAD_IMG("sport", 17, R.drawable.cycling);
		UPLOAD_IMG("sport", 18, R.drawable.scuba_diving);
		// UPLOAD_IMG("animals", 19, R.drawable.golf);
		// UPLOAD_IMG("animals", 20, R.drawable.skateboarding);
		//
		/*
		 * table badroom
		 */
		UPLOAD_IMG("bedroom", 1, R.drawable.pillow);
		UPLOAD_IMG("bedroom", 2, R.drawable.sheet);
		UPLOAD_IMG("bedroom", 3, R.drawable.blanket);
		UPLOAD_IMG("bedroom", 4, R.drawable.alarm_clock);
		UPLOAD_IMG("bedroom", 5, R.drawable.mattress);
		//
		UPLOAD_IMG("bedroom", 6, R.drawable.chest_of_drawers);
		UPLOAD_IMG("bedroom", 7, R.drawable.dresser);
		UPLOAD_IMG("bedroom", 8, R.drawable.bunk_bed);
		UPLOAD_IMG("bedroom", 9, R.drawable.light_switch);
		UPLOAD_IMG("bedroom", 10, R.drawable.lamp);
		//
		UPLOAD_IMG("bedroom", 11, R.drawable.light_bulb);
		UPLOAD_IMG("bedroom", 12, R.drawable.candle);
		UPLOAD_IMG("bedroom", 13, R.drawable.chest);
		/*
		 * table bathroom
		 */
		UPLOAD_IMG("bathroom", 1, R.drawable.bathtub);
		UPLOAD_IMG("bathroom", 2, R.drawable.soap);
		UPLOAD_IMG("bathroom", 3, R.drawable.shower);
		UPLOAD_IMG("bathroom", 4, R.drawable.sink);
		UPLOAD_IMG("bathroom", 5, R.drawable.faucet);
		//
		UPLOAD_IMG("bathroom", 6, R.drawable.toilet);
		UPLOAD_IMG("bathroom", 7, R.drawable.shampoo);
		UPLOAD_IMG("bathroom", 8, R.drawable.toilet_paper);
		UPLOAD_IMG("bathroom", 9, R.drawable.toilet_brush);
		UPLOAD_IMG("bathroom", 10, R.drawable.plunger);
		//
		UPLOAD_IMG("bathroom", 11, R.drawable.tissue);
		UPLOAD_IMG("bathroom", 12, R.drawable.marble);
		UPLOAD_IMG("bathroom", 13, R.drawable.towel);
		UPLOAD_IMG("bathroom", 14, R.drawable.hairbrush);
		UPLOAD_IMG("bathroom", 15, R.drawable.comb);
		//
		UPLOAD_IMG("bathroom", 16, R.drawable.mirror);
		UPLOAD_IMG("bathroom", 17, R.drawable.hair_dryer);
		UPLOAD_IMG("bathroom", 18, R.drawable.perfume);
		UPLOAD_IMG("bathroom", 19, R.drawable.deodorant);
		UPLOAD_IMG("bathroom", 20, R.drawable.lipstick);
		//
		UPLOAD_IMG("bathroom", 21, R.drawable.toothbrush);
		UPLOAD_IMG("bathroom", 22, R.drawable.nail_polish);
		UPLOAD_IMG("bathroom", 23, R.drawable.mascara);
		UPLOAD_IMG("bathroom", 24, R.drawable.eye_iner);
		UPLOAD_IMG("bathroom", 25, R.drawable.powder);
		//
		UPLOAD_IMG("bathroom", 26, R.drawable.floss);
		UPLOAD_IMG("bathroom", 27, R.drawable.toothpaste);
		UPLOAD_IMG("bathroom", 28, R.drawable.razor);
		UPLOAD_IMG("bathroom", 29, R.drawable.razor_blade);
		UPLOAD_IMG("bathroom", 30, R.drawable.tweezers);
		// /
		UPLOAD_IMG("bathroom", 31, R.drawable.shaving_cream);
		UPLOAD_IMG("bathroom", 32, R.drawable.nail_clippers);
		UPLOAD_IMG("bathroom", 33, R.drawable.hairspray);
		UPLOAD_IMG("bathroom", 34, R.drawable.cotton_swab);
		UPLOAD_IMG("bathroom", 35, R.drawable.medicine_cabinet);
		/*
		 * table kitchen
		 */
		UPLOAD_IMG("kitchen", 1, R.drawable.fridge);
		UPLOAD_IMG("kitchen", 2, R.drawable.stove);
		UPLOAD_IMG("kitchen", 3, R.drawable.toaster);
		UPLOAD_IMG("kitchen", 4, R.drawable.can_opener);
		UPLOAD_IMG("kitchen", 5, R.drawable.jar);
		//
		UPLOAD_IMG("kitchen", 6, R.drawable.mixer);
		UPLOAD_IMG("kitchen", 7, R.drawable.blender);
		UPLOAD_IMG("kitchen", 8, R.drawable.microwave);
		UPLOAD_IMG("kitchen", 9, R.drawable.food_processor);
		UPLOAD_IMG("kitchen", 10, R.drawable.can);
		//
		UPLOAD_IMG("kitchen", 11, R.drawable.sink);
		UPLOAD_IMG("kitchen", 12, R.drawable.paper_towel);
		UPLOAD_IMG("kitchen", 13, R.drawable.sponge);
		UPLOAD_IMG("kitchen", 14, R.drawable.dishwasher);
		UPLOAD_IMG("kitchen", 15, R.drawable.coffee_maker);
		//
		UPLOAD_IMG("kitchen", 16, R.drawable.coffee_grinder);
		UPLOAD_IMG("kitchen", 17, R.drawable.pot);
		UPLOAD_IMG("kitchen", 18, R.drawable.skillet);
		UPLOAD_IMG("kitchen", 19, R.drawable.tray);
		UPLOAD_IMG("kitchen", 20, R.drawable.teakettle);
		//
		UPLOAD_IMG("kitchen", 21, R.drawable.knife);
		UPLOAD_IMG("kitchen", 22, R.drawable.cutting_board);
		UPLOAD_IMG("kitchen", 23, R.drawable.whisk);
		UPLOAD_IMG("kitchen", 24, R.drawable.rolling_pin);
		UPLOAD_IMG("kitchen", 25, R.drawable.muffin_pan);
		//
		UPLOAD_IMG("kitchen", 26, R.drawable.spatula);
		UPLOAD_IMG("kitchen", 27, R.drawable.grater);
		UPLOAD_IMG("kitchen", 28, R.drawable.colander);
		UPLOAD_IMG("kitchen", 29, R.drawable.strainer);
		UPLOAD_IMG("kitchen", 30, R.drawable.measuring_cup);
		// /
		UPLOAD_IMG("kitchen", 31, R.drawable.measuring_spoon);
		UPLOAD_IMG("kitchen", 32, R.drawable.apron);
		/*
		 * table phong đựng đồ
		 */
		UPLOAD_IMG("utilitytool", 1, R.drawable.bleach);
		UPLOAD_IMG("utilitytool", 2, R.drawable.broom);
		UPLOAD_IMG("utilitytool", 3, R.drawable.clothes_line);
		UPLOAD_IMG("utilitytool", 4, R.drawable.clothes_pin);
		UPLOAD_IMG("utilitytool", 5, R.drawable.dustpan);
		//
		UPLOAD_IMG("utilitytool", 6, R.drawable.fly_swatter);
		UPLOAD_IMG("utilitytool", 7, R.drawable.hanger);
		UPLOAD_IMG("utilitytool", 8, R.drawable.iron);
		UPLOAD_IMG("utilitytool", 9, R.drawable.dryer);
		UPLOAD_IMG("utilitytool", 10, R.drawable.ironing_board);
		//
		UPLOAD_IMG("utilitytool", 11, R.drawable.dirty_clothes_hamper);
		UPLOAD_IMG("utilitytool", 12, R.drawable.lighter);
		UPLOAD_IMG("utilitytool", 13, R.drawable.matchbook);
		UPLOAD_IMG("utilitytool", 14, R.drawable.mop);
		UPLOAD_IMG("utilitytool", 15, R.drawable.scrub_brush);
		//
		UPLOAD_IMG("utilitytool", 16, R.drawable.spray_bottle);
		UPLOAD_IMG("utilitytool", 17, R.drawable.trash_bag);
		UPLOAD_IMG("utilitytool", 18, R.drawable.trash_can);
		UPLOAD_IMG("utilitytool", 19, R.drawable.vacuum_cleaner);
		UPLOAD_IMG("utilitytool", 20, R.drawable.washing_machine);
		//
		UPLOAD_IMG("utilitytool", 21, R.drawable.knife);

	}

	//

	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
