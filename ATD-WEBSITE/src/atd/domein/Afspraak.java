/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package atd.domein;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Afspraak {
	private Klant klant;
	private User monteur;
	private Auto auto;
	private Date datum;
	private String omschrijving;
	private AfspraakStatus status;
	private int uren;
	private int afspraakID;
	private double totaalBedrag;
	private ArrayList<Onderdeel> onderdelen = new ArrayList<>();

	public Afspraak(Klant klant, User monteur, Auto auto, Date datum,
			String omschrijving, AfspraakStatus st) {

		this.klant = klant;
		this.monteur = monteur;
		this.auto = auto;
		this.datum = datum;
		this.omschrijving = omschrijving;
		this.status = st;

	}

	public Klant getKlant() {
		return klant;
	}

	public User getMonteur() {
		return monteur;
	}

	public Auto getAuto() {
		return auto;
	}

	public Date getDatum() {
		return datum;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public AfspraakStatus getStatus() {
		return status;
	}

	public String getStatusString() {
		return status.name();
	}

	public void setStatus(AfspraakStatus st) {
		status = st;
	}

	public void setId(int id) {
		afspraakID = id;
	}

	public int getID() {
		return afspraakID;
	}

	public void setUren(int uur) {
		this.uren = uur;
	}

	public int getUren() {
		return uren;
	}

	public String getDateString() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String deDatum = df.format(datum);
		return deDatum;
	}

	public void setAlleOnderdelen(ArrayList<Onderdeel> alleOnderdelen) {
		onderdelen = alleOnderdelen;
	}

	public ArrayList<Onderdeel> getAlleOnderdelen() {
		return onderdelen;
	}

	public void setTotaalPrijs(double bedrag) {
		this.totaalBedrag = bedrag;
	}

	public double getTotaalPrijs() {
		return totaalBedrag;
	}
}
