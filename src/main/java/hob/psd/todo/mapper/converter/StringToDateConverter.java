package hob.psd.todo.mapper.converter;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lgunti on 001, Dec 01.
 */
public class StringToDateConverter extends BidirectionalConverter<String,Date> {
    private final String pattern;
    private final ThreadLocal<SimpleDateFormat> dateFormats = new ThreadLocal<SimpleDateFormat>();

    /**
     * @return a SimpleDateFormat instance safe for use in the current thread
     */
    private SimpleDateFormat getDateFormat() {
        /*SimpleDateFormat formatter = dateFormats.get();
        if (formatter == null) {
            formatter = new SimpleDateFormat(pattern, Locale.US);
            dateFormats.set(formatter);
        }
        return formatter;*/
        return new SimpleDateFormat(pattern);
    }

    /**
     * Constructs a new instance of DateToStringConverter capable of
     * parsing and constructing Date strings according to the provided format.
     *
     * @param format the format descriptor, processed according to the rules
     * defined in {@link java.text.SimpleDateFormat}
     */
    public StringToDateConverter(final String format) {
        this.pattern = format;
    }

    @Override
    public String convertFrom(Date source, Type<String> destinationType) {
        return getDateFormat().format(source);
    }

    @Override
    public Date convertTo(String source, Type<Date> destinationType) {
        try {
            return getDateFormat().parse(source);
        } catch (ParseException e) {
            return null;
        }
    }
}
