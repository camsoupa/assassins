package com.nbs.client.assassins.network;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AttackResponse extends Response {
	@JsonProperty("hit")
	public boolean hit;
	
	@JsonProperty("target_life")
	public int targetLife;

	@Override
	public String toString() {
		return "AttackResponse [hit=" + hit + ", time=" + time
				+ ", targetLife=" + targetLife + ", status=" + status
				+ ", message=" + message + "]";
	}
}
