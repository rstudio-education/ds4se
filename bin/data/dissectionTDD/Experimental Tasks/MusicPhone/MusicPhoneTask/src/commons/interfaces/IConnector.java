
package commons.interfaces;


import java.util.List;
import commons.dataClasses.ConcertInfo;
import dataConnectors.LastFmConnectionException;

public interface IConnector {
	List<String> getTopFansForArtist(String artist) throws LastFmConnectionException;
	List<String> getTopArtistsByFan(String fanName) throws LastFmConnectionException;
	List<ConcertInfo> getConcertsForArtist(String artist) throws LastFmConnectionException;
}
