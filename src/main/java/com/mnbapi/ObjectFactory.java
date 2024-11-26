
package com.mnbapi;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mnbapi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _GetCurrenciesRequestBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrenciesRequestBody");
    private static final QName _GetCurrencies_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrencies");
    private static final QName _GetCurrenciesResponseBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrenciesResponseBody");
    private static final QName _GetCurrenciesResponse_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrenciesResponse");
    private static final QName _GetCurrencyUnitsRequestBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrencyUnitsRequestBody");
    private static final QName _GetCurrencyUnits_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrencyUnits");
    private static final QName _GetCurrencyUnitsResponseBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrencyUnitsResponseBody");
    private static final QName _GetCurrencyUnitsResponse_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrencyUnitsResponse");
    private static final QName _GetCurrentExchangeRatesRequestBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrentExchangeRatesRequestBody");
    private static final QName _GetCurrentExchangeRates_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrentExchangeRates");
    private static final QName _GetCurrentExchangeRatesResponseBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrentExchangeRatesResponseBody");
    private static final QName _GetCurrentExchangeRatesResponse_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrentExchangeRatesResponse");
    private static final QName _GetDateIntervalRequestBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetDateIntervalRequestBody");
    private static final QName _GetDateInterval_QNAME = new QName("http://www.mnb.hu/webservices/", "GetDateInterval");
    private static final QName _GetDateIntervalResponseBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetDateIntervalResponseBody");
    private static final QName _GetDateIntervalResponse_QNAME = new QName("http://www.mnb.hu/webservices/", "GetDateIntervalResponse");
    private static final QName _GetExchangeRatesRequestBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetExchangeRatesRequestBody");
    private static final QName _GetExchangeRates_QNAME = new QName("http://www.mnb.hu/webservices/", "GetExchangeRates");
    private static final QName _GetExchangeRatesResponseBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetExchangeRatesResponseBody");
    private static final QName _GetExchangeRatesResponse_QNAME = new QName("http://www.mnb.hu/webservices/", "GetExchangeRatesResponse");
    private static final QName _GetInfoRequestBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetInfoRequestBody");
    private static final QName _GetInfo_QNAME = new QName("http://www.mnb.hu/webservices/", "GetInfo");
    private static final QName _GetInfoResponseBody_QNAME = new QName("http://www.mnb.hu/webservices/", "GetInfoResponseBody");
    private static final QName _GetInfoResponse_QNAME = new QName("http://www.mnb.hu/webservices/", "GetInfoResponse");
    private static final QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private static final QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private static final QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private static final QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private static final QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private static final QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private static final QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private static final QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private static final QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private static final QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private static final QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private static final QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private static final QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private static final QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private static final QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private static final QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private static final QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private static final QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private static final QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private static final QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private static final QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private static final QName _GetInfoResponseBodyGetInfoResult_QNAME = new QName("http://www.mnb.hu/webservices/", "GetInfoResult");
    private static final QName _GetExchangeRatesResponseBodyGetExchangeRatesResult_QNAME = new QName("http://www.mnb.hu/webservices/", "GetExchangeRatesResult");
    private static final QName _GetExchangeRatesRequestBodyStartDate_QNAME = new QName("http://www.mnb.hu/webservices/", "startDate");
    private static final QName _GetExchangeRatesRequestBodyEndDate_QNAME = new QName("http://www.mnb.hu/webservices/", "endDate");
    private static final QName _GetExchangeRatesRequestBodyCurrencyNames_QNAME = new QName("http://www.mnb.hu/webservices/", "currencyNames");
    private static final QName _GetDateIntervalResponseBodyGetDateIntervalResult_QNAME = new QName("http://www.mnb.hu/webservices/", "GetDateIntervalResult");
    private static final QName _GetCurrentExchangeRatesResponseBodyGetCurrentExchangeRatesResult_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrentExchangeRatesResult");
    private static final QName _GetCurrencyUnitsResponseBodyGetCurrencyUnitsResult_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrencyUnitsResult");
    private static final QName _GetCurrenciesResponseBodyGetCurrenciesResult_QNAME = new QName("http://www.mnb.hu/webservices/", "GetCurrenciesResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mnbapi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCurrenciesRequestBody }
     * 
     * @return
     *     the new instance of {@link GetCurrenciesRequestBody }
     */
    public GetCurrenciesRequestBody createGetCurrenciesRequestBody() {
        return new GetCurrenciesRequestBody();
    }

    /**
     * Create an instance of {@link GetCurrenciesResponseBody }
     * 
     * @return
     *     the new instance of {@link GetCurrenciesResponseBody }
     */
    public GetCurrenciesResponseBody createGetCurrenciesResponseBody() {
        return new GetCurrenciesResponseBody();
    }

    /**
     * Create an instance of {@link GetCurrencyUnitsRequestBody }
     * 
     * @return
     *     the new instance of {@link GetCurrencyUnitsRequestBody }
     */
    public GetCurrencyUnitsRequestBody createGetCurrencyUnitsRequestBody() {
        return new GetCurrencyUnitsRequestBody();
    }

    /**
     * Create an instance of {@link GetCurrencyUnitsResponseBody }
     * 
     * @return
     *     the new instance of {@link GetCurrencyUnitsResponseBody }
     */
    public GetCurrencyUnitsResponseBody createGetCurrencyUnitsResponseBody() {
        return new GetCurrencyUnitsResponseBody();
    }

    /**
     * Create an instance of {@link GetCurrentExchangeRatesRequestBody }
     * 
     * @return
     *     the new instance of {@link GetCurrentExchangeRatesRequestBody }
     */
    public GetCurrentExchangeRatesRequestBody createGetCurrentExchangeRatesRequestBody() {
        return new GetCurrentExchangeRatesRequestBody();
    }

    /**
     * Create an instance of {@link GetCurrentExchangeRatesResponseBody }
     * 
     * @return
     *     the new instance of {@link GetCurrentExchangeRatesResponseBody }
     */
    public GetCurrentExchangeRatesResponseBody createGetCurrentExchangeRatesResponseBody() {
        return new GetCurrentExchangeRatesResponseBody();
    }

    /**
     * Create an instance of {@link GetDateIntervalRequestBody }
     * 
     * @return
     *     the new instance of {@link GetDateIntervalRequestBody }
     */
    public GetDateIntervalRequestBody createGetDateIntervalRequestBody() {
        return new GetDateIntervalRequestBody();
    }

    /**
     * Create an instance of {@link GetDateIntervalResponseBody }
     * 
     * @return
     *     the new instance of {@link GetDateIntervalResponseBody }
     */
    public GetDateIntervalResponseBody createGetDateIntervalResponseBody() {
        return new GetDateIntervalResponseBody();
    }

    /**
     * Create an instance of {@link GetExchangeRatesRequestBody }
     * 
     * @return
     *     the new instance of {@link GetExchangeRatesRequestBody }
     */
    public GetExchangeRatesRequestBody createGetExchangeRatesRequestBody() {
        return new GetExchangeRatesRequestBody();
    }

    /**
     * Create an instance of {@link GetExchangeRatesResponseBody }
     * 
     * @return
     *     the new instance of {@link GetExchangeRatesResponseBody }
     */
    public GetExchangeRatesResponseBody createGetExchangeRatesResponseBody() {
        return new GetExchangeRatesResponseBody();
    }

    /**
     * Create an instance of {@link GetInfoRequestBody }
     * 
     * @return
     *     the new instance of {@link GetInfoRequestBody }
     */
    public GetInfoRequestBody createGetInfoRequestBody() {
        return new GetInfoRequestBody();
    }

    /**
     * Create an instance of {@link GetInfoResponseBody }
     * 
     * @return
     *     the new instance of {@link GetInfoResponseBody }
     */
    public GetInfoResponseBody createGetInfoResponseBody() {
        return new GetInfoResponseBody();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrenciesRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrenciesRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrenciesRequestBody")
    public JAXBElement<GetCurrenciesRequestBody> createGetCurrenciesRequestBody(GetCurrenciesRequestBody value) {
        return new JAXBElement<>(_GetCurrenciesRequestBody_QNAME, GetCurrenciesRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrenciesRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrenciesRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrencies")
    public JAXBElement<GetCurrenciesRequestBody> createGetCurrencies(GetCurrenciesRequestBody value) {
        return new JAXBElement<>(_GetCurrencies_QNAME, GetCurrenciesRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrenciesResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrenciesResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrenciesResponseBody")
    public JAXBElement<GetCurrenciesResponseBody> createGetCurrenciesResponseBody(GetCurrenciesResponseBody value) {
        return new JAXBElement<>(_GetCurrenciesResponseBody_QNAME, GetCurrenciesResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrenciesResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrenciesResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrenciesResponse")
    public JAXBElement<GetCurrenciesResponseBody> createGetCurrenciesResponse(GetCurrenciesResponseBody value) {
        return new JAXBElement<>(_GetCurrenciesResponse_QNAME, GetCurrenciesResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrencyUnitsRequestBody")
    public JAXBElement<GetCurrencyUnitsRequestBody> createGetCurrencyUnitsRequestBody(GetCurrencyUnitsRequestBody value) {
        return new JAXBElement<>(_GetCurrencyUnitsRequestBody_QNAME, GetCurrencyUnitsRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrencyUnits")
    public JAXBElement<GetCurrencyUnitsRequestBody> createGetCurrencyUnits(GetCurrencyUnitsRequestBody value) {
        return new JAXBElement<>(_GetCurrencyUnits_QNAME, GetCurrencyUnitsRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrencyUnitsResponseBody")
    public JAXBElement<GetCurrencyUnitsResponseBody> createGetCurrencyUnitsResponseBody(GetCurrencyUnitsResponseBody value) {
        return new JAXBElement<>(_GetCurrencyUnitsResponseBody_QNAME, GetCurrencyUnitsResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrencyUnitsResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrencyUnitsResponse")
    public JAXBElement<GetCurrencyUnitsResponseBody> createGetCurrencyUnitsResponse(GetCurrencyUnitsResponseBody value) {
        return new JAXBElement<>(_GetCurrencyUnitsResponse_QNAME, GetCurrencyUnitsResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrentExchangeRatesRequestBody")
    public JAXBElement<GetCurrentExchangeRatesRequestBody> createGetCurrentExchangeRatesRequestBody(GetCurrentExchangeRatesRequestBody value) {
        return new JAXBElement<>(_GetCurrentExchangeRatesRequestBody_QNAME, GetCurrentExchangeRatesRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrentExchangeRates")
    public JAXBElement<GetCurrentExchangeRatesRequestBody> createGetCurrentExchangeRates(GetCurrentExchangeRatesRequestBody value) {
        return new JAXBElement<>(_GetCurrentExchangeRates_QNAME, GetCurrentExchangeRatesRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrentExchangeRatesResponseBody")
    public JAXBElement<GetCurrentExchangeRatesResponseBody> createGetCurrentExchangeRatesResponseBody(GetCurrentExchangeRatesResponseBody value) {
        return new JAXBElement<>(_GetCurrentExchangeRatesResponseBody_QNAME, GetCurrentExchangeRatesResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrentExchangeRatesResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrentExchangeRatesResponse")
    public JAXBElement<GetCurrentExchangeRatesResponseBody> createGetCurrentExchangeRatesResponse(GetCurrentExchangeRatesResponseBody value) {
        return new JAXBElement<>(_GetCurrentExchangeRatesResponse_QNAME, GetCurrentExchangeRatesResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateIntervalRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDateIntervalRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetDateIntervalRequestBody")
    public JAXBElement<GetDateIntervalRequestBody> createGetDateIntervalRequestBody(GetDateIntervalRequestBody value) {
        return new JAXBElement<>(_GetDateIntervalRequestBody_QNAME, GetDateIntervalRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateIntervalRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDateIntervalRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetDateInterval")
    public JAXBElement<GetDateIntervalRequestBody> createGetDateInterval(GetDateIntervalRequestBody value) {
        return new JAXBElement<>(_GetDateInterval_QNAME, GetDateIntervalRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateIntervalResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDateIntervalResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetDateIntervalResponseBody")
    public JAXBElement<GetDateIntervalResponseBody> createGetDateIntervalResponseBody(GetDateIntervalResponseBody value) {
        return new JAXBElement<>(_GetDateIntervalResponseBody_QNAME, GetDateIntervalResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateIntervalResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDateIntervalResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetDateIntervalResponse")
    public JAXBElement<GetDateIntervalResponseBody> createGetDateIntervalResponse(GetDateIntervalResponseBody value) {
        return new JAXBElement<>(_GetDateIntervalResponse_QNAME, GetDateIntervalResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetExchangeRatesRequestBody")
    public JAXBElement<GetExchangeRatesRequestBody> createGetExchangeRatesRequestBody(GetExchangeRatesRequestBody value) {
        return new JAXBElement<>(_GetExchangeRatesRequestBody_QNAME, GetExchangeRatesRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetExchangeRates")
    public JAXBElement<GetExchangeRatesRequestBody> createGetExchangeRates(GetExchangeRatesRequestBody value) {
        return new JAXBElement<>(_GetExchangeRates_QNAME, GetExchangeRatesRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetExchangeRatesResponseBody")
    public JAXBElement<GetExchangeRatesResponseBody> createGetExchangeRatesResponseBody(GetExchangeRatesResponseBody value) {
        return new JAXBElement<>(_GetExchangeRatesResponseBody_QNAME, GetExchangeRatesResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetExchangeRatesResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetExchangeRatesResponse")
    public JAXBElement<GetExchangeRatesResponseBody> createGetExchangeRatesResponse(GetExchangeRatesResponseBody value) {
        return new JAXBElement<>(_GetExchangeRatesResponse_QNAME, GetExchangeRatesResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetInfoRequestBody")
    public JAXBElement<GetInfoRequestBody> createGetInfoRequestBody(GetInfoRequestBody value) {
        return new JAXBElement<>(_GetInfoRequestBody_QNAME, GetInfoRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoRequestBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoRequestBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetInfo")
    public JAXBElement<GetInfoRequestBody> createGetInfo(GetInfoRequestBody value) {
        return new JAXBElement<>(_GetInfo_QNAME, GetInfoRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetInfoResponseBody")
    public JAXBElement<GetInfoResponseBody> createGetInfoResponseBody(GetInfoResponseBody value) {
        return new JAXBElement<>(_GetInfoResponseBody_QNAME, GetInfoResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoResponseBody }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoResponseBody }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetInfoResponse")
    public JAXBElement<GetInfoResponseBody> createGetInfoResponse(GetInfoResponseBody value) {
        return new JAXBElement<>(_GetInfoResponse_QNAME, GetInfoResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QName }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetInfoResult", scope = GetInfoResponseBody.class)
    public JAXBElement<String> createGetInfoResponseBodyGetInfoResult(String value) {
        return new JAXBElement<>(_GetInfoResponseBodyGetInfoResult_QNAME, String.class, GetInfoResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetExchangeRatesResult", scope = GetExchangeRatesResponseBody.class)
    public JAXBElement<String> createGetExchangeRatesResponseBodyGetExchangeRatesResult(String value) {
        return new JAXBElement<>(_GetExchangeRatesResponseBodyGetExchangeRatesResult_QNAME, String.class, GetExchangeRatesResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "startDate", scope = GetExchangeRatesRequestBody.class)
    public JAXBElement<String> createGetExchangeRatesRequestBodyStartDate(String value) {
        return new JAXBElement<>(_GetExchangeRatesRequestBodyStartDate_QNAME, String.class, GetExchangeRatesRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "endDate", scope = GetExchangeRatesRequestBody.class)
    public JAXBElement<String> createGetExchangeRatesRequestBodyEndDate(String value) {
        return new JAXBElement<>(_GetExchangeRatesRequestBodyEndDate_QNAME, String.class, GetExchangeRatesRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "currencyNames", scope = GetExchangeRatesRequestBody.class)
    public JAXBElement<String> createGetExchangeRatesRequestBodyCurrencyNames(String value) {
        return new JAXBElement<>(_GetExchangeRatesRequestBodyCurrencyNames_QNAME, String.class, GetExchangeRatesRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetDateIntervalResult", scope = GetDateIntervalResponseBody.class)
    public JAXBElement<String> createGetDateIntervalResponseBodyGetDateIntervalResult(String value) {
        return new JAXBElement<>(_GetDateIntervalResponseBodyGetDateIntervalResult_QNAME, String.class, GetDateIntervalResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrentExchangeRatesResult", scope = GetCurrentExchangeRatesResponseBody.class)
    public JAXBElement<String> createGetCurrentExchangeRatesResponseBodyGetCurrentExchangeRatesResult(String value) {
        return new JAXBElement<>(_GetCurrentExchangeRatesResponseBodyGetCurrentExchangeRatesResult_QNAME, String.class, GetCurrentExchangeRatesResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrencyUnitsResult", scope = GetCurrencyUnitsResponseBody.class)
    public JAXBElement<String> createGetCurrencyUnitsResponseBodyGetCurrencyUnitsResult(String value) {
        return new JAXBElement<>(_GetCurrencyUnitsResponseBodyGetCurrencyUnitsResult_QNAME, String.class, GetCurrencyUnitsResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "currencyNames", scope = GetCurrencyUnitsRequestBody.class)
    public JAXBElement<String> createGetCurrencyUnitsRequestBodyCurrencyNames(String value) {
        return new JAXBElement<>(_GetExchangeRatesRequestBodyCurrencyNames_QNAME, String.class, GetCurrencyUnitsRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.mnb.hu/webservices/", name = "GetCurrenciesResult", scope = GetCurrenciesResponseBody.class)
    public JAXBElement<String> createGetCurrenciesResponseBodyGetCurrenciesResult(String value) {
        return new JAXBElement<>(_GetCurrenciesResponseBodyGetCurrenciesResult_QNAME, String.class, GetCurrenciesResponseBody.class, value);
    }

}
