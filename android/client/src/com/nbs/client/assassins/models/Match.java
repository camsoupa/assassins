package com.nbs.client.assassins.models;
import java.util.Arrays;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.android.gms.maps.model.LatLng;


/**
 * @author cam
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Match {

	public Match(String name, String pw, long tStart, LatLng nw, LatLng se, double aRange, double hRange, int tEscape) {
		this.name = name; 
		this.password = pw; 
		this.startTime = tStart;
		this.attackRange = aRange;   this.huntRange = hRange; this.escapeTime = tEscape;
		this.nwCorner = new LatLngData(nw); this.seCorner = new LatLngData(se);
	}
	
	public Match() {}

	@JsonProperty("token")
	public String token;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("start_time")
	public long startTime;
	
	@JsonProperty("players")
	public Player[] players;
	
	@JsonProperty("password")
	public String password;
	
	@JsonProperty("nw_corner")
	public LatLngData nwCorner;
	
	@JsonProperty("se_corner")
	public LatLngData seCorner;
	
	@JsonProperty("attack_range")
	public double attackRange;
	
	@JsonProperty("hunt_range")
	public double huntRange;
	
	@JsonProperty("escape_time")
	public int escapeTime;

	@Override
	public String toString() {
		return "Match [token=" + token + ", name=" + name + ", startTime="
				+ startTime + ", players=" + Arrays.toString(players)
				+ ", password=" + password + ", nwCorner=" + nwCorner
				+ ", seCorner=" + seCorner + ", attackRange=" + attackRange
				+ ", huntRange=" + huntRange + ", escapeTime=" + escapeTime
				+ "]";
	}

	public LatLng getSECornerLatLng() {
		return seCorner != null ? seCorner.toLatLng() : null;
	}

	public LatLng getNWCornerLatLng() {
		return nwCorner != null ? nwCorner.toLatLng() : null;
	}

	
}
