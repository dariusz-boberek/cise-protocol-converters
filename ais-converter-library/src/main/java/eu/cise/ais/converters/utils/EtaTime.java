package eu.cise.ais.converters.utils;

public class EtaTime {

    /**
     * Starting from an ETA in the format "17-09 14:30"
     * It returns the time in the format of 14:30:00.000Z
     *
     * In case the hour is 24 it translates it to 00
     * and in case the minutes are 60 it translates it to 00
     *
     * @param eta the estimates time of arrival.
     * @return the date formatted as HH:MM:00.000Z
     */
    String getTime(String eta) {
        return getDefaultHours(eta, "00") + ":" + getDefaultMinutes(eta, "00") + ":00.000Z";
    }

    private String getHours(String etaStr) {
        return getHoursColumnMinutes(etaStr).split(":")[0];
    }

    private String getMinutes(String etaStr) {
        return getHoursColumnMinutes(etaStr).split(":")[1];
    }

    private String getHoursColumnMinutes(String etaStr) {
        return etaStr.split(" ")[1];
    }

    private String getDefaultHours(String eta, String defaultValue) {
        return getHours(eta).equals("24") ? defaultValue : getHours(eta);
    }

    private String getDefaultMinutes(String eta, String defaultValue) {
        return getMinutes(eta).equals("60") ? defaultValue : getMinutes(eta);
    }

}
