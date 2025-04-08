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
        private Object district;
        private String adcode;
        private String location;
        private String level;

        // Getters and setters...
    }
}
