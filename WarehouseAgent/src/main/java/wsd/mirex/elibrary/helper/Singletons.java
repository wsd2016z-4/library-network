package wsd.mirex.elibrary.helper;

import wsd.mirex.elibrary.message.checkAvailability.CheckAvailabilityRequest;
import wsd.mirex.elibrary.message.getBook.GetBookRequest;
import wsd.mirex.elibrary.message.getLocalization.GetLocalizationRequest;
import wsd.mirex.elibrary.message.issueBook.IssueBookRequest;
import wsd.mirex.elibrary.message.orderBook.OrderBookRequest;
import wsd.mirex.elibrary.message.orderDrone.OrderDroneRequest;
import wsd.mirex.elibrary.message.releaseBook.ReleaseBookRequest;
import wsd.mirex.elibrary.message.returnBook.ReturnBookRequest;
import wsd.mirex.elibrary.message.returnBookByDron.ReturnBookByDronRequest;
import wsd.mirex.elibrary.message.searchBook.SearchBookRequest;

/**
 * Created by pj on 22.01.17.
 */
public class Singletons
{
    private static CheckAvailabilityRequest checkAvailabilityRequest = new CheckAvailabilityRequest();
    private static GetBookRequest getBookRequest = new GetBookRequest();
    private static GetLocalizationRequest getLocalizationRequest = new GetLocalizationRequest();
    private static IssueBookRequest issueBookRequest = new IssueBookRequest();
    private static OrderBookRequest orderBookRequest = new OrderBookRequest();
    private static OrderDroneRequest orderDronRequest = new OrderDroneRequest();
    private static ReleaseBookRequest releaseBookRequest = new ReleaseBookRequest();
    private static ReturnBookRequest returnBookRequest = new ReturnBookRequest();
    private static ReturnBookByDronRequest returnBookByDronRequest = new ReturnBookByDronRequest();
    private static SearchBookRequest searchBookRequest = new SearchBookRequest();

    public static CheckAvailabilityRequest getCheckAvailabilityRequest() {
        return checkAvailabilityRequest;
    }

    public static void setCheckAvailabilityRequest(CheckAvailabilityRequest checkAvailabilityRequest) {
        Singletons.checkAvailabilityRequest = checkAvailabilityRequest;
    }

    public static GetBookRequest getGetBookRequest() {
        return getBookRequest;
    }

    public static void setGetBookRequest(GetBookRequest getBookRequest) {
        Singletons.getBookRequest = getBookRequest;
    }

    public static GetLocalizationRequest getGetLocalizationRequest() {
        return getLocalizationRequest;
    }

    public static void setGetLocalizationRequest(GetLocalizationRequest getLocalizationRequest) {
        Singletons.getLocalizationRequest = getLocalizationRequest;
    }

    public static IssueBookRequest getIssueBookRequest() {
        return issueBookRequest;
    }

    public static void setIssueBookRequest(IssueBookRequest issueBookRequest) {
        Singletons.issueBookRequest = issueBookRequest;
    }

    public static OrderBookRequest getOrderBookRequest() {
        return orderBookRequest;
    }

    public static void setOrderBookRequest(OrderBookRequest orderBookRequest) {
        Singletons.orderBookRequest = orderBookRequest;
    }

    public static OrderDroneRequest getOrderDronRequest() {
        return orderDronRequest;
    }

    public static void setOrderDronRequest(OrderDroneRequest orderDronRequest) {
        Singletons.orderDronRequest = orderDronRequest;
    }

    public static ReleaseBookRequest getReleaseBookRequest() {
        return releaseBookRequest;
    }

    public static void setReleaseBookRequest(ReleaseBookRequest releaseBookRequest) {
        Singletons.releaseBookRequest = releaseBookRequest;
    }

    public static ReturnBookRequest getReturnBookRequest() {
        return returnBookRequest;
    }

    public static void setReturnBookRequest(ReturnBookRequest returnBookRequest) {
        Singletons.returnBookRequest = returnBookRequest;
    }

    public static ReturnBookByDronRequest getReturnBookByDronRequest() {
        return returnBookByDronRequest;
    }

    public static void setReturnBookByDronRequest(ReturnBookByDronRequest returnBookByDronRequest) {
        Singletons.returnBookByDronRequest = returnBookByDronRequest;
    }

    public static SearchBookRequest getSearchBookRequest() {
        return searchBookRequest;
    }

    public static void setSearchBookRequest(SearchBookRequest searchBookRequest) {
        Singletons.searchBookRequest = searchBookRequest;
    }
}
