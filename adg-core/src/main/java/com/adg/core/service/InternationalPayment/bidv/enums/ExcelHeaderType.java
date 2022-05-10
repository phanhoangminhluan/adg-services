package com.adg.core.service.InternationalPayment.bidv.enums;

import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.ParserUtils;
import org.apache.poi.ss.usermodel.CellType;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.10 22:48
 */
public enum ExcelHeaderType {

    STRING(String.class, CellType.STRING, Objects::nonNull),
    DOUBLE(Double.class, CellType.NUMERIC, raw -> {
        if (raw == null) return false;
        if (raw.equals("0")) return true;
        return ParserUtils.toDouble(raw, 0) != 0;

    }),
    BOOLEAN(Boolean.class, CellType.BOOLEAN, raw -> {
        if (raw == null) return false;
        return raw.equalsIgnoreCase("false") || raw.equalsIgnoreCase("true");
    }),
    DATE(ZonedDateTime.class, CellType.NUMERIC, raw -> {
        if (raw == null) return false;
        return DateTimeUtils.verifyDateTimeFormat(raw, DateTimeUtils.FMT_01);
    });

    public final Class javaType;
    public final CellType excelType;
    public final  Function<String, Boolean> verifyMethod;

    <T> ExcelHeaderType(Class<T> javaType, CellType excelType, Function<String, Boolean> verifyMethod) {
        this.javaType = javaType;
        this.excelType = excelType;
        this.verifyMethod = verifyMethod;
    }

}
