package cz.knowit.ai.bank.database.client;

/**
 * Enum that represents nationalities with their phone number regex.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public enum Nationality {

    CZECH_REPUBLIC("\\+420\\d{9}"),
    SLOVAKIA("\\+421\\d{9}"),
    AUSTRIA("\\+43\\d{9}"),
    GERMANY("\\+49\\d{9}"),
    POLAND("\\+48\\d{9}"),
    HUNGARY("\\+36\\d{9}"),
    SLOVENIA("\\+386\\d{9}"),
    CROATIA("\\+385\\d{9}"),
    ITALY("\\+39\\d{9}"),
    SPAIN("\\+34\\d{9}"),
    PORTUGAL("\\+351\\d{9}"),
    FRANCE("\\+33\\d{9}"),
    BELGIUM("\\+32\\d{9}"),
    NETHERLANDS("\\+31\\d{9}"),
    LUXEMBOURG("\\+352\\d{9}"),
    DENMARK("\\+45\\d{9}"),
    SWEDEN("\\+46\\d{9}"),
    FINLAND("\\+358\\d{9}"),
    ESTONIA("\\+372\\d{9}"),
    LATVIA("\\+371\\d{9}"),
    LITHUANIA("\\+370\\d{9}"),
    IRELAND("\\+353\\d{9}"),
    GREAT_BRITAIN("\\+44\\d{9}"),
    CYPRUS("\\+357\\d{9}"),
    MALTA("\\+356\\d{9}"),
    ROMANIA("\\+40\\d{9}"),
    BULGARIA("\\+359\\d{9}"),
    GREECE("\\+30\\d{9}"),
    ICELAND("\\+354\\d{9}"),
    NORWAY("\\+47\\d{9}"),
    LIECHTENSTEIN("\\+423\\d{9}"),
    SWITZERLAND("\\+41\\d{9}"),
    MONACO("\\+377\\d{9}"),
    ANDORRA("\\+376\\d{9}"),
    SAN_MARINO("\\+378\\d{9}"),
    VATICAN("\\+379\\d{9}"),
    UKRAINE("\\+380\\d{9}"),
    BELARUS("\\+375\\d{9}"),
    MOLDOVA("\\+373\\d{9}"),
    RUSSIA("\\+7\\d{9}"),
    TURKEY("\\+90\\d{9}"),
    ARMENIA("\\+374\\d{9}"),
    ;

    private final String phoneNumberRegex;

    Nationality(String phoneNumberRegex) {
        this.phoneNumberRegex = phoneNumberRegex;
    }

    public String getPhoneNumberRegex() {
        return phoneNumberRegex;
    }
}
