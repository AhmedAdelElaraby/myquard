package com.teraninja.guard.DataClient;

import com.teraninja.guard.Model.DataCV;
import com.teraninja.guard.Model.DataCity;
import com.teraninja.guard.Model.DataCompany;
import com.teraninja.guard.Model.DataDetilsMessage;
import com.teraninja.guard.Model.DataLogin;
import com.teraninja.guard.Model.DataLogout;
import com.teraninja.guard.Model.DataMassege;
import com.teraninja.guard.Model.DataMessageCompany;
import com.teraninja.guard.Model.DataNewPassWord;
import com.teraninja.guard.Model.DataNotifications;
import com.teraninja.guard.Model.DataOfferCompany;
import com.teraninja.guard.Model.DataOfferguard;
import com.teraninja.guard.Model.DataOllStaff;
import com.teraninja.guard.Model.DataPackage;
import com.teraninja.guard.Model.DataPackageCompany;
import com.teraninja.guard.Model.DataPayCvs;
import com.teraninja.guard.Model.DataPayment;
import com.teraninja.guard.Model.DataPaymentCompany;
import com.teraninja.guard.Model.DataProblem;
import com.teraninja.guard.Model.DataReasons;
import com.teraninja.guard.Model.DataRecuestCode;
import com.teraninja.guard.Model.DataResponcemessage;
import com.teraninja.guard.Model.DataResponseoffersjob;
import com.teraninja.guard.Model.DataSendLogin;
import com.teraninja.guard.Model.DataSendPayment;
import com.teraninja.guard.Model.DataSendRegister;
import com.teraninja.guard.Model.DataSendSearchstaff;
import com.teraninja.guard.Model.DataSendfilterguard;
import com.teraninja.guard.Model.DataShowUserByUser;
import com.teraninja.guard.Model.DataSubscribeCompany;
import com.teraninja.guard.Model.DataTems;
import com.teraninja.guard.Model.DataUpdatephone;
import com.teraninja.guard.Model.DataWorkNatures;
import com.teraninja.guard.Model.GetRegister;
import com.teraninja.guard.Model.GetVerifyAccoun;
import com.teraninja.guard.Model.ModelCity;
import com.teraninja.guard.Model.ModelCondation;
import com.teraninja.guard.Model.ModelHome;
import com.teraninja.guard.Model.ModelShowOllguardAcceptOffer;
import com.teraninja.guard.Model.ModelTypeCompany;
import com.teraninja.guard.Model.Modelsendmessage;
import com.teraninja.guard.Model.Profile;
import com.teraninja.guard.Model.Reedollnav;
import com.teraninja.guard.Model.SendCvs;
import com.teraninja.guard.Model.SendDataChatGrupe;
import com.teraninja.guard.Model.SendDataOfferJob;
import com.teraninja.guard.Model.SendDataRecuestCode;
import com.teraninja.guard.Model.SendDataShowOllguardAcceptOffer;
import com.teraninja.guard.Model.SendDataUpdtePassword;
import com.teraninja.guard.Model.SendNewPassWoed;
import com.teraninja.guard.Model.SendVerifyAccoun;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceApi {

    @POST("api/login")
    Observable<DataLogin> getCountries(@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage ,@Body DataSendLogin DataLogin);
    @POST("api/register")
    Observable<GetRegister> getCreateEmailUser(@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage ,@Body DataSendRegister dataSendRegister);

    @POST("api/request/code")
    Observable<DataRecuestCode>reguestcode(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage , @Body SendDataRecuestCode dataRecuestCode);

    @POST("api/verify/account")
    Observable<GetVerifyAccoun>verifyaccoun(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage , @Body SendVerifyAccoun verifyAccoun);

    @POST("api/reset/password")
    Observable<DataNewPassWord>getResstPassWord(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage , @Body SendNewPassWoed sendNewPassWord);

    @POST("api/company/register")
    Observable<DataCompany> getCreateEmailcompany(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage , @Body RequestBody sendCompany);
    @POST("api/home")
    Observable<ModelHome> getNews(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/user/profile")
    Observable<Profile> getProfile(@Header("Authorization") String Token,@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/job/{guard_job_type_id}/conditions")
    Observable<ModelCondation> getCondation(@Path("guard_job_type_id") int id,@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage);
    @POST("api/cities")
    Observable<ModelCity>getcitys(@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage);
    @POST("api/cities/{city_id}/districts")
    Observable<DataCity> getDistricts(@Path("city_id") int id,@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage);
    /////
    @POST("api/guard/packages")
    Observable<DataPackage> getPackage(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);

    ////
    @POST("api/company/types")
    Observable<ModelTypeCompany>getTypeCompany(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/update/cv")
    Observable<DataCV> getCV(@Header("Authorization") String Token,@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage, @Body RequestBody SendCV);
    @POST("api/create-pay-form")
    Observable<DataPayment> getPayment(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body DataSendPayment dataSendPayment);
    @POST("api/company/packages")
    Observable<DataPackageCompany>getpackageCompany(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/filter/guards")
    Observable<DataOllStaff>getOllStaff(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage ,@Body DataSendSearchstaff sendSearchstaff);
    @POST("api/create-pay-form")
    Observable<DataPaymentCompany> getPaymentcompany(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body DataSendPayment dataSendPayment);
    @POST("api/notifications")
    Observable<DataNotifications> getollnotifications(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/contact/reasons")
    Observable<DataReasons> getreasons(@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/contact/us")
    Observable<DataProblem> SendProblem(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body RequestBody requestBody);
    @POST("api/company/guards")
    Observable<DataPayCvs> getguardscompany(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @FormUrlEncoded
    @POST("api/buy/cvs")
    Observable<SendCvs> SendCvs(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Field("users[]") ArrayList<String> users);
    @POST("api/company/job_offers")
    Observable<DataOfferCompany> getofferscompany(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/subscribe/company/packages")
    Observable<DataSubscribeCompany> getSubscribeCompany(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);

    @POST("api/guard/job_offers")
    Observable<DataOfferguard> getoffersguard(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/subscribe/guard/packages")
    Observable<DataSubscribeCompany> getSubscribeguard(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/user/{user_id}")
    Observable<DataShowUserByUser> showuserbyuser(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage,@Path("user_id") int id);
    @POST("api/guard/conversations")
    Observable<DataMassege> getmessageguardr(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/company/guards")
    Observable<DataPayCvs> getguardscompanyfilter(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage,@Body DataSendfilterguard DataSendfilterguard);
    @POST("api/logout")
    Observable<DataLogout> getLogout(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/company/conversations")
    Observable<DataMessageCompany> MessageCompany(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/terms")
    Observable<DataTems> getterms (@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/work/natures")
    Observable<DataWorkNatures> worknatures (@Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);

    @POST("api/create/job/offer")
    Observable<DataResponseoffersjob> sendoffersjob (@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body SendDataOfferJob offerJob);
    @POST("api/notifications/all/read")
    Observable<Reedollnav> Reedollnav (@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage);
    @POST("api/guard/accept/job_offers/{jopOffer_id}")
    Observable<Reedollnav> Accept(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage,@Path("jopOffer_id") int id);
    @POST("api/guard/reject/job_offers/{jopOffer_id}")
    Observable<Reedollnav> Reject(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage,@Path("jopOffer_id") int id);

    @POST("api/update/company/profile")
    Observable<Profile> updateProfileCompany (@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body RequestBody updateProfileCompany);
    @POST("api/update/password")
    Observable<Reedollnav> UpdatePassword(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body SendDataUpdtePassword updtePassword);
    @POST("api/send/code")
    Observable<DataUpdatephone> UpdatePhone(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body SendDataRecuestCode recuestCode);
    @POST("api/update/phone")
    Observable<DataUpdatephone> SendcodeUpdate(@Header("Authorization") String Token, @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body SendVerifyAccoun verifyAccoun);
    @POST("api/update/guard/profile")
    Observable<DataCV> UpdateCvGuard(@Header("Authorization") String Token ,@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage, @Body RequestBody sendCV);

    @POST("api/guards/job_offers")
    Observable<ModelShowOllguardAcceptOffer> getguaedAcceptoffer(@Header("Authorization") String Token ,@Header("Accept") String Accept,@Header("Accept-Language") String AcceptLanguage, @Body SendDataShowOllguardAcceptOffer ollguardAcceptOffer);

    @POST("api/create/conversation")
    Observable<DataResponcemessage> getResponcemessage(@Header("Authorization") String Token , @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body SendDataChatGrupe grupe);
    @POST("api/company/conversations/{conversation_id}/messages")
    Observable<DataDetilsMessage> getmessageDitalse(@Header("Authorization") String Token , @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Path("conversation_id") int id);
    @POST("api/send/message")
    Observable<Modelsendmessage> SendMessage(@Header("Authorization") String Token , @Header("Accept") String Accept, @Header("Accept-Language") String AcceptLanguage, @Body RequestBody message);

}
