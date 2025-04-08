package gdAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: WangZhiWei
 * @Time: 2025/4/8 15:45
 */
@Data
public class Geo {
    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<Geocode> geocodes;


    @Data
    public static class Geocode {
        @JsonProperty("formatted_address")
        private String formattedAddress;

        private String country;
        private String province;

        @JsonProperty("citycode")
        private String cityCode;

        private String city;
        private List<String> district;
        private List<String> township;
        private Neighborhood neighborhood;
        private Building building;

        private String adcode;
        private List<String> street;
        private List<String> number;
        private String location;
        private String level;

        // Getters and setters...
    }
    @Data
    public static class Neighborhood {
        private List<String> name;
        private List<String> type;

        // Getters and setters...
    }
    @Data
    public static class Building {
        private List<String> name;
        private List<String> type;

        // Getters and setters...
    }
}
