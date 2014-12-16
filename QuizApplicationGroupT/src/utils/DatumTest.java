package utils;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import utils.datumScratch.*;

public class DatumTest {

	private Calendar systeemDatum;
	private DateFormat dateformat;

	@Before
	public void setUp() {
		dateformat = new SimpleDateFormat("dd/MM/yyyy");
		systeemDatum = Calendar.getInstance();
	}

	@Test
	public void test1_datum_geldige_constructor_met_datumObject_Aanvaard() {
		String temp = dateformat.format(systeemDatum.getTime());
		Datum d1 = new Datum(temp);
		Datum d2 = new Datum(d1);
		assertEquals(d1, d2);
	}

	@Test
	public void test2_Datum_geldige_constructor_met_string_Aanvaard() {
		String temp = dateformat.format(systeemDatum.getTime());
		Datum d1 = new Datum(temp);
		assertTrue(d1 != null);
	}

	@Test
	public void test3_Datum_geldige_constructor_met_intwaardes_Aanvaard() {
		Datum d1 = new Datum(10, 11, 2014);
		assertTrue(d1 != null);
	}

	@Test
	public void test4_Datum_AmerikaansFormaat_Aanvaard() {
		Datum d1 = new Datum(10, 11, 2014);
		assertEquals("2014/11/10", d1.getDatumInAmerikaansFormaat());
	}

	@Test
	public void test5_Datum_europeesFormaat_Aanvaard() {
		Datum d1 = new Datum(10, 11, 2014);
		assertEquals("10/11/2014", d1.getDatumInEuropeesFormaat());
	}

	@Test
	public void test6_Datum_compareTo_gelijk_Aanvaard() {
		Datum d1 = new Datum(10, 11, 2014);
		Datum d2 = new Datum(10, 11, 2014);
		assertTrue(d1.compareTo(d2) == 0);
	}

	@Test
	public void test7_Datum_compareTo_kleiner_Aanvaard() {
		Datum d1 = new Datum(10, 11, 2014);
		Datum d2 = new Datum(9, 11, 2014);
		assertEquals(-1, d1.compareTo(d2));
	}

	@Test
	public void test8_Datum_compareTo_groter_Aanvaard() {
		Datum d1 = new Datum(8, 11, 2014);
		Datum d2 = new Datum(9, 11, 2014);
		assertEquals(1, d1.compareTo(d2));
	}

	@Test
	public void test9_Datum_equals_gelijke_waardes_verschillend_object_Aanvaard() {
		Datum d1 = new Datum(8, 11, 2014);
		Datum d2 = new Datum(8, 11, 2014);
		assertEquals(true, d1.equals(d2));
	}

	@Test
	public void test10_Datum_equals_gelijke_waardes_zelfde_object_Aanvaard() {
		Datum d1 = new Datum(8, 11, 2014);
		Datum d2 = d1;
		assertEquals(true, d1.equals(d2));
	}

	@Test
	public void test11_Datum_equals_verschillende_waardes_Aanvaard() {
		Datum d1 = new Datum(9, 11, 2014);
		Datum d2 = new Datum(8, 11, 2014);
		assertEquals(false, d1.equals(d2));
	}

	@Test
	public void test12_Datum_verschilInJaren_Aanvaard() {
		Datum d1 = new Datum(1, 3, 2007);
		Datum d2 = new Datum(3, 1, 2009);
		assertEquals(1, d1.verschilInJaren(d2));
	}

	@Test
	public void test13_Datum_verschilInDagen_Aanvaard() {
		Datum d1 = new Datum(1, 3, 2007);
		Datum d2 = new Datum(3, 1, 2014);
		assertEquals(2500, d1.verschilInDagen(d2));
	}

	@Test
	public void test14_Datum_verschilInMaanden_Aanvaard() {
		Datum d1 = new Datum(1, 3, 2007);
		Datum d2 = new Datum(3, 1, 2014);
		assertEquals(82, d1.verschilInMaanden(d2));
	}

	@Test
	public void test15_datum_veranderDatum_object_Aanvaard() {
		Datum d1 = new Datum(1, 3, 2007);
		d1.veranderDatum(2500);
		assertEquals("2014/1/3", d1.getDatumInAmerikaansFormaat());
	}
	
	@Test
	public void test16_datum_veranderDatum__nieuw_object_Aanvaard() {
		Datum d1 = new Datum(1, 3, 2007);
		Datum d2 = d1.veranderDatumInNieuwObject(2500);
		assertEquals("2014/1/3", d2.getDatumInAmerikaansFormaat());
	}

	@SuppressWarnings("unused")
	@Test(expected = Exception.class)
	public void test17_datum_constructor_int_maanden_Negatief() {
		Datum d1 = new Datum(1, 13, 2007);
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test18_datum_constructor_int_dagen_Negatief() {
		Datum d1 = new Datum(-1, 12, 2007);
	}
	@SuppressWarnings("unused")
	@Test(expected = Exception.class)
	public void test19_Datum_constructor_int_jaren_Negatief() {
		Datum d1 = new Datum(1, 12, -1);
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test20_Datum_constructor_string_niet_numeriek() {
		Datum d1 = new Datum("ditIsEenTest");
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test21_Datum_constructor_string_foute_maand_Negatief() {
		Datum d1 = new Datum("01/13/2007");
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test22_Datum_constructor_string_foute_dag_Negatief() {
		Datum d1 = new Datum("32/01/2007");
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test23_Datum_constructor_string_fout_jaar_Negatief() {
		Datum d1 = new Datum("01/12/-1");
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test24_Datum_constructor_string_fout_dag_niet_schrikkeljaar_Negatief() {
		Datum d1 = new Datum("29/02/2011");
	}
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void test25_Datum_constructor_int_fout_dag_niet_schrikkeljaar_Negatief() {
		Datum d1 = new Datum(29, 02, 2011);
	}

}
