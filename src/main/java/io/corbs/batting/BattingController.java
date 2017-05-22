package io.corbs.batting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

class Batting {
    @JsonProperty
    private Integer avg;
    @JsonProperty
    private Integer ab;
    @JsonProperty
    private Integer hr;
    @JsonProperty
    private Integer rbi;
    @JsonProperty
    private int hits;
    @JsonProperty
    private int runs;
    @JsonProperty
    private String playerId;

    public String getPlayerId() {
        return playerId;
    }

    public Integer getAvg() {
        return avg;
    }

    public Integer getAb() {
        return ab;
    }

    public Integer getHr() {
        return hr;
    }

    public Integer getRbi() {
        return rbi;
    }

    public Integer getHits() {
        return hits;
    }

    public Integer getRuns() {
        return runs;
    }

    void setAvg(Integer avg) {
        this.avg = avg;
    }

    void setAb(Integer ab) {
        this.ab = ab;
    }

    void setHr(Integer hr) {
        this.hr = hr;
    }

    void setRbi(Integer rbi) {
        this.rbi = rbi;
    }

    void setHits(int hits) {
        this.hits = hits;
    }

    void setRuns(int runs) {
        this.runs = runs;
    }

    void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}

class Response {

    @JsonInclude(Include.NON_EMPTY)
    private Map<String, Object> meta;

    @JsonProperty
    private Object data;

    public Response(Object data) {
        this.data = data;
        this.meta = new HashMap<>();
    }

    public void add(String key, Object value) {
        this.meta.put(key, value);
    }

    public void remove(String key) {
        this.meta.remove(key);
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }
}

@RestController
public class BattingController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/player/{playerId}")
    public Response batting(@PathVariable String playerId) {
        Batting batting = new Batting();
        if(!StringUtils.isEmpty(playerId)) {
            batting.setAvg(342);
            batting.setHr(714);
            batting.setAb(8399);
            batting.setRbi(2214);
            batting.setHits(2873);
            batting.setRuns(2174);
            batting.setPlayerId(playerId);
        }

        if(port != null) {
            Response response = new Response(batting);
            response.add("port", port);
            return response;
        }

        return new Response(batting);
    }

}
