package com.wsd.warehouseagent.helper;

import com.wsd.warehouseagent.message.checkAvailability.CheckAvailabilityRequest;
import com.wsd.warehouseagent.message.getBook.GetBookRequest;
import com.wsd.warehouseagent.message.getLocalization.GetLocalizationRequest;
import com.wsd.warehouseagent.message.issueBook.IssueBookRequest;
import com.wsd.warehouseagent.message.orderBook.OrderBookRequest;
import com.wsd.warehouseagent.message.orderDrone.OrderDroneRequest;
import com.wsd.warehouseagent.message.releaseBook.ReleaseBookRequest;
import com.wsd.warehouseagent.message.returnBook.ReturnBookRequest;
import com.wsd.warehouseagent.message.returnBookByDron.ReturnBookByDronRequest;
import com.wsd.warehouseagent.message.searchBook.SearchBookRequest;

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
