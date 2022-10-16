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
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    InterfaceApi interFaceAPI;
    private static final String BASE_URL="https://guard.teraninjadev.com/";

    public static RetrofitAPI instans;


    public RetrofitAPI() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        interFaceAPI=retrofit.create(InterfaceApi.class);

    }




    public static RetrofitAPI getInstans() {
        if (null==instans){
            instans=new RetrofitAPI();
        }
        return instans;
    }



    public Observable<DataLogin> getPosts(String Accept,String l, DataSendLogin dataLogin){

        return interFaceAPI.getCountries(Accept,l,dataLogin);
    }
    public Observable<GetRegister> getDataRegister(String Accept, String l, DataSendRegister register){

        return interFaceAPI.getCreateEmailUser(Accept,l,register);
    }
    public Observable<DataRecuestCode> getDataRecuestCode(String Accept, String l, SendDataRecuestCode dataRecuestCode){
        return interFaceAPI.reguestcode(Accept,l,dataRecuestCode);
    }
    public Observable<GetVerifyAccoun> getverifyaccoun(String Accept, String l, SendVerifyAccoun sendVerifyAccoun){
        return interFaceAPI.verifyaccoun(Accept,l,sendVerifyAccoun);
    }

    public Observable<DataNewPassWord> passWord(String Accept, String l, SendNewPassWoed sendNewPassWoed){
        return interFaceAPI.getResstPassWord(Accept,l,sendNewPassWoed);
    }
    public Observable<DataCompany> Emailcompany(String Accept, String l, RequestBody sendCompany){
        return interFaceAPI.getCreateEmailcompany(Accept,l,sendCompany);
    }
    public Observable<ModelHome> getnews(String Accept, String l){
        return interFaceAPI.getNews(Accept,l);
    }
    public Observable<Profile> getProfile(String token,String Accept,String l){
        return interFaceAPI.getProfile(token,Accept,l);
    }
    public Observable<ModelCondation> getCondation(int id,String Accept,String AcceptLanguage){
        return interFaceAPI.getCondation(id,Accept,AcceptLanguage);
    }
    public Observable<ModelCity> getcity(String Accept,String AcceptLanguage){

        return interFaceAPI.getcitys(Accept,AcceptLanguage);
    }
    public Observable<DataCity> getDistricts(int id,String Accept,String AcceptLanguage){
        return interFaceAPI.getDistricts(id,Accept,AcceptLanguage);
    }
    public Observable<DataPackage> getPackage(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getPackage(Token,Accept,AcceptLanguage);
    }
    public Observable<ModelTypeCompany> getTypeCompany(String Accept, String AcceptLanguage){
        return interFaceAPI.getTypeCompany(Accept,AcceptLanguage);
    }
    public Observable<DataCV> getCV(String token,String Accept, String AcceptLanguage,RequestBody CV){
        return interFaceAPI.getCV(token,Accept,AcceptLanguage,CV);
    }
    public Observable<DataPayment> getPayment(String token, String Accept, String AcceptLanguage, DataSendPayment dataSendPayment){
        return interFaceAPI.getPayment(token,Accept,AcceptLanguage,dataSendPayment);
    }
    public Observable<DataPackageCompany>getPackageCompany(String Token,String Accept,String AcceptLanguage){
        return interFaceAPI.getpackageCompany(Token,Accept,AcceptLanguage);

    }
    public Observable<DataOllStaff>getsearchstaff(String Token, String Accept, String AcceptLanguage, DataSendSearchstaff searchstaff){
        return interFaceAPI.getOllStaff(Token,Accept,AcceptLanguage,searchstaff);

    }
    public Observable<DataPaymentCompany> getPaymentcompany(String token, String Accept, String AcceptLanguage, DataSendPayment dataSendPayment){
        return interFaceAPI.getPaymentcompany(token,Accept,AcceptLanguage,dataSendPayment);
    }
    public Observable<DataNotifications> getollnotifications(String token, String Accept, String AcceptLanguage){
        return interFaceAPI.getollnotifications(token,Accept,AcceptLanguage);
    }
    public Observable<DataReasons> getReasons(String Accept, String AcceptLanguage){
        return interFaceAPI.getreasons(Accept,AcceptLanguage);
    }
    public Observable<DataProblem> sendProblem(String Token,String Accept, String AcceptLanguage, RequestBody sendDataProblem){
        return interFaceAPI.SendProblem(Token,Accept,AcceptLanguage,sendDataProblem);
    }
    public Observable<DataPayCvs> getguardscompany(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getguardscompany(Token,Accept,AcceptLanguage);
    }
    public Observable<SendCvs> SendCvs(String Token, String Accept, String AcceptLanguage, ArrayList<String> Users){
        return interFaceAPI.SendCvs(Token,Accept,AcceptLanguage,Users);
    }
    public Observable<DataOfferCompany> getofferscompany(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getofferscompany(Token,Accept,AcceptLanguage);
    }
    public Observable<DataSubscribeCompany> getSubscribecompany(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getSubscribeCompany(Token,Accept,AcceptLanguage);}

    public Observable<DataOfferguard> getofferguard(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getoffersguard(Token,Accept,AcceptLanguage);}
    public Observable<DataSubscribeCompany> getSubscribeguard(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getSubscribeguard(Token,Accept,AcceptLanguage);}
    public Observable<DataShowUserByUser> ShowUserByUser(String Token, String Accept, String AcceptLanguage,int id){
        return interFaceAPI.showuserbyuser(Token,Accept,AcceptLanguage,id);}
    public Observable<DataMassege> getmessageguard(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getmessageguardr(Token,Accept,AcceptLanguage);}
    public Observable<DataPayCvs> getguardcompanyfilter(String Token, String Accept, String AcceptLanguage, DataSendfilterguard DataSendfilterguard){
        return interFaceAPI.getguardscompanyfilter(Token,Accept,AcceptLanguage,DataSendfilterguard);
    }
    public Observable<DataLogout> getlogout(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.getLogout(Token,Accept,AcceptLanguage);
    }
    public Observable<DataMessageCompany> Messagecompany(String Token, String Accept, String AcceptLanguage){
        return interFaceAPI.MessageCompany(Token,Accept,AcceptLanguage);
    }
    public Observable<DataTems> gettems( String Accept, String AcceptLanguage){
        return interFaceAPI.getterms(Accept,AcceptLanguage);
    }
    public Observable<DataWorkNatures> worknatures(String Accept, String AcceptLanguage){
        return interFaceAPI.worknatures(Accept,AcceptLanguage);
    }
    public Observable<DataResponseoffersjob> Sendofferjob(String token, String Accept, String AcceptLanguage, SendDataOfferJob offerJob){
        return interFaceAPI.sendoffersjob(token,Accept,AcceptLanguage,offerJob);
    }
    public Observable<Reedollnav> Reedollnav(String token, String Accept, String AcceptLanguage){
        return interFaceAPI.Reedollnav(token,Accept,AcceptLanguage);
    }
    public Observable<Reedollnav> Accepted(String token, String Accept, String AcceptLanguage,int id){
        return interFaceAPI.Accept(token,Accept,AcceptLanguage,id);
    }
    public Observable<Reedollnav> Reject(String token, String Accept, String AcceptLanguage,int id){
        return interFaceAPI.Reject(token,Accept,AcceptLanguage,id);
    }
    public Observable<Profile> UpdateProfileCommpany(String token, String Accept, String AcceptLanguage, RequestBody company){
        return interFaceAPI.updateProfileCompany(token,Accept,AcceptLanguage,company);
    }
    public Observable<Reedollnav> UpdatePassword(String token, String Accept, String AcceptLanguage, SendDataUpdtePassword updtePassword){
        return interFaceAPI.UpdatePassword(token,Accept,AcceptLanguage,updtePassword);
    }
    public Observable<DataUpdatephone> UpdatePhone(String token, String Accept, String AcceptLanguage, SendDataRecuestCode recuestCode){
        return interFaceAPI.UpdatePhone(token,Accept,AcceptLanguage,recuestCode);
    }
    public Observable<DataUpdatephone> Updatecode(String token, String Accept, String AcceptLanguage, SendVerifyAccoun recuestCode){
        return interFaceAPI.SendcodeUpdate(token,Accept,AcceptLanguage,recuestCode);
    }
    public Observable<DataCV> UpdateCvguard(String token, String Accept, String AcceptLanguage, RequestBody recuestCode){
        return interFaceAPI.UpdateCvGuard(token,Accept,AcceptLanguage,recuestCode);
    }

    public Observable<ModelShowOllguardAcceptOffer> OllguardAcceptOffer(String token, String Accept, String AcceptLanguage, SendDataShowOllguardAcceptOffer showOllguardAcceptOffer){
        return interFaceAPI.getguaedAcceptoffer(token,Accept,AcceptLanguage,showOllguardAcceptOffer);
    }
    public Observable<DataResponcemessage> ResponceMessage(String token, String Accept, String AcceptLanguage, SendDataChatGrupe grupe){
        return interFaceAPI.getResponcemessage(token,Accept,AcceptLanguage,grupe);
    }
    public Observable<DataDetilsMessage> getmessageDitalse(String token, String Accept, String AcceptLanguage, int id){
        return interFaceAPI.getmessageDitalse(token,Accept,AcceptLanguage,id);
    }
    public Observable<Modelsendmessage> SendMessage(String token, String Accept, String AcceptLanguage, RequestBody mesage){
        return interFaceAPI.SendMessage(token,Accept,AcceptLanguage,mesage);
    }
}


