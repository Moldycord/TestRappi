package danieer.galvez.testrappi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "results"
})
public class Videos {

    @JsonProperty("results")
    private List<VideoResult> results = null;

    @JsonProperty("results")
    public List<VideoResult> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<VideoResult> results) {
        this.results = results;
    }

}