package com.teraninja.guard.UI;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teraninja.guard.DataClient.RetrofitAPI;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


public class MoveViewModel extends ViewModel {

    MutableLiveData<DataLogin> data = new MutableLiveData<>();
    MutableLiveData<GetRegister> dataregister = new MutableLiveData<>();
    MutableLiveData<DataRecuestCode> dataRecuestCode = new MutableLiveData<>();
    MutableLiveData<GetVerifyAccoun> getVerifyAccoun = new MutableLiveData<>();
    MutableLiveData<DataNewPassWord> dataNewPassWord = new MutableLiveData<>();
    MutableLiveData<DataCompany> dataCompany = new MutableLiveData<>();
    public MutableLiveData<ModelHome> getNews = new MutableLiveData<>();
    public MutableLiveData<Profile> getProfile = new MutableLiveData<>();
    public MutableLiveData<ModelCondation> getcondation = new MutableLiveData<>();
    public MutableLiveData<ModelCity> getcity = new MutableLiveData<>();
    public MutableLiveData<DataCity> getDistricts = new MutableLiveData<>();
    public MutableLiveData<DataPackage> getPackage = new MutableLiveData<>();
    public MutableLiveData<ModelTypeCompany> getTypeCompany = new MutableLiveData<>();
    public MutableLiveData<DataCV> CV = new MutableLiveData<>();
    public MutableLiveData<DataPayment> Payment=new MutableLiveData<>();
    public MutableLiveData<DataPackageCompany> getPackageCompany = new MutableLiveData<>();
    public MutableLiveData<DataOllStaff> getollstaff = new MutableLiveData<>();
    public MutableLiveData<DataPaymentCompany> Paymentcompany=new MutableLiveData<>();
    public MutableLiveData<DataNotifications> Notifications= new MutableLiveData<>();
    public MutableLiveData<DataReasons> Reasonslivedata= new MutableLiveData<>();
    public MutableLiveData<DataProblem> problem= new MutableLiveData<>();
    public MutableLiveData<DataPayCvs> getguardscompany= new MutableLiveData<>();
    public MutableLiveData<SendCvs> SendCvs= new MutableLiveData<>();
    public MutableLiveData<DataOfferCompany> getoffrescompany= new MutableLiveData<>();
    public MutableLiveData<DataSubscribeCompany> dataSubscribeCompany= new MutableLiveData<>();
    public MutableLiveData<DataOfferguard> getoffreguard= new MutableLiveData<>();
    public MutableLiveData<DataSubscribeCompany> dataSubscribeguard= new MutableLiveData<>();
    public MutableLiveData<DataShowUserByUser> ShowUserByUser = new MutableLiveData<>();
    public MutableLiveData<DataMassege> getmessage = new MutableLiveData<>();
    public MutableLiveData<DataPayCvs> getguardscompanyfilter= new MutableLiveData<>();
    public MutableLiveData<DataLogout> getlogout= new MutableLiveData<>();
    public MutableLiveData<DataMessageCompany> MessageCompany= new MutableLiveData<>();
    public MutableLiveData<DataTems> gettems=new MutableLiveData<>();
    public MutableLiveData<DataWorkNatures> worknat=new MutableLiveData<>();
    public MutableLiveData<DataResponseoffersjob> dataoffersjob=new MutableLiveData<>();
    public MutableLiveData<Reedollnav> Reedol=new MutableLiveData<>();
    public MutableLiveData<Reedollnav> Accepted=new MutableLiveData<>();
    public MutableLiveData<Reedollnav> Reject=new MutableLiveData<>();
    public MutableLiveData<Profile> updatecompany=new MutableLiveData<>();
    public MutableLiveData<Reedollnav> updatepassword=new MutableLiveData<>();
    public MutableLiveData<DataUpdatephone> updatephone= new MutableLiveData<>();
    public MutableLiveData<DataUpdatephone> SendcodeUpdate= new MutableLiveData<>();
    public MutableLiveData<DataCV> UpdateCvguard = new MutableLiveData<>();
    public MutableLiveData<ModelShowOllguardAcceptOffer> showOllguardAcceptOffer= new MutableLiveData<>();
    public MutableLiveData<DataResponcemessage> ResponceMessage= new MutableLiveData<>();
    public  MutableLiveData<DataDetilsMessage> getmessageDitalse= new MutableLiveData<>();
    public MutableLiveData<Modelsendmessage> modelsendmessageMutableLiveData = new MutableLiveData<>();
    public boolean ollselected=false;

    public void  getlogin(String Accept,String l, DataSendLogin dataLogin){
        Observable observable = RetrofitAPI.getInstans().getPosts(Accept,l,dataLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
               data.setValue((DataLogin) o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void  getcreateuser(String Accept,String l, DataSendRegister sendRegister){
        Observable observable = RetrofitAPI.getInstans().getDataRegister(Accept,l,sendRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataregister.setValue((GetRegister) o);
                Log.i("error data:", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error data:", ""+e);
                if (e instanceof HttpException) {
                ResponseBody responseBody =((HttpException)e)
                        .response().errorBody();
                JSONObject object = null;
                try {
                    object = new JSONObject(responseBody.string());
                    GetRegister data = new GetRegister(object.getInt("status"),object.getInt("code"),object.getString("message"));
                    dataregister.setValue(data);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }}

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getDataRequestcode(String Accept,String l, SendDataRecuestCode sendDataRecuestCode){
        Observable observable = RetrofitAPI.getInstans().getDataRecuestCode(Accept,l,sendDataRecuestCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataRecuestCode.setValue((DataRecuestCode) o);
                Log.i("error data:", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error data:", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getVerifyAccount(String Accept,String l, SendVerifyAccoun sendVerifyAccoun){
        Observable observable = RetrofitAPI.getInstans().getverifyaccoun(Accept,l,sendVerifyAccoun)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getVerifyAccoun.setValue((GetVerifyAccoun) o);
                Log.i("error data:", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error data:", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getNews(String Accept,String l){
        Observable observable = RetrofitAPI.getInstans().getnews(Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getNews.setValue((ModelHome) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.i("error", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  Company(String Accept,String l, RequestBody sendCompany){
        Observable observable= RetrofitAPI.getInstans().Emailcompany(Accept,l,sendCompany)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
                 //.subscribe(dataresponse -> dataCompany.setValue(dataresponse.getData()),
//                        error -> {ResponseBody responseBody =((HttpException)error)
//                        .response().errorBody();
//                            JSONObject object = new JSONObject(responseBody.string());
//
//                            Log.d("ahmed", " "+object);
//                        },
//                        () -> Log.e("ahmed","complete"));

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataCompany.setValue((DataCompany) o);
                Log.e("ahmed",((DataCompany) o).getMessage());

            }

            @Override
            public void onError(@NonNull Throwable e) {

                    if (e instanceof HttpException) {
                        HttpException httpException = (HttpException) e;
                        ResponseBody responseBody = httpException
                                .response().errorBody();
                        JSONObject object = null;
                        try {
                            object = new JSONObject(responseBody.string());
                            DataCompany data = new DataCompany(object.getInt("status"), object.getInt("code"), object.getString("message"));
                            dataCompany.setValue(data);

                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        //Log.d("ahmed", " " + object);
                    }else {
                        Log.d("ahmed", "onError: "+e.getMessage().toString());
                    }



            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void  newPassword(String Accept,String l, SendNewPassWoed sendVerifyAccoun){
        Observable observable = RetrofitAPI.getInstans().passWord(Accept,l,sendVerifyAccoun)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataNewPassWord.setValue((DataNewPassWord) o);
                Log.i("error data:", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error data:", ""+e);
                ResponseBody responseBody =((HttpException)e)
                        .response().errorBody();
                JSONObject object = null;
                try {
                    object = new JSONObject(responseBody.string());
                    DataNewPassWord data = new DataNewPassWord(object.getInt("status"),object.getInt("code"),object.getString("message"));
                    dataNewPassWord.setValue(data);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getProfile(String token,String Accept,String l){
        Observable observable = RetrofitAPI.getInstans().getProfile(token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getProfile.setValue((Profile) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error", ""+e);
                if (e instanceof HttpException) {
                ResponseBody responseBody =((HttpException)e)
                        .response().errorBody();
                JSONObject object = null;
                try {
                    object = new JSONObject(responseBody.string());
                    Profile data = new Profile(object.getInt("status"),object.getInt("code"),object.getString("message"));
                    getProfile.setValue(data);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }}
                else {

                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getCondation(int id,String Accept,String AcceptLanguage){
        Observable observable = RetrofitAPI.getInstans().getCondation(id,Accept,AcceptLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getcondation.setValue((ModelCondation) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
//                Log.i("error", ""+e);
//                ResponseBody responseBody =((HttpException)e)
//                        .response().errorBody();
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(responseBody.string());
//                    ModelCondation data = new ModelCondation(object.getInt("status"),object.getInt("code"),object.getString("message"));
//                    getProfile.setValue(data);
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getCity(String Accept,String AcceptLanguage){
        Observable observable = RetrofitAPI.getInstans().getcity(Accept,AcceptLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getcity.setValue((ModelCity) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
//                Log.i("error", ""+e);
//                ResponseBody responseBody =((HttpException)e)
//                        .response().errorBody();
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(responseBody.string());
//                    ModelCondation data = new ModelCondation(object.getInt("status"),object.getInt("code"),object.getString("message"));
//                    getProfile.setValue(data);
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getDistricts(int id ,String Accept,String AcceptLanguage){
        Observable observable = RetrofitAPI.getInstans().getDistricts(id,Accept,AcceptLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getDistricts.setValue((DataCity) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
//                Log.i("error", ""+e);
//                ResponseBody responseBody =((HttpException)e)
//                        .response().errorBody();
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(responseBody.string());
//                    ModelCondation data = new ModelCondation(object.getInt("status"),object.getInt("code"),object.getString("message"));
//                    getProfile.setValue(data);
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getPackage(String token ,String Accept,String AcceptLanguage){
        Observable observable = RetrofitAPI.getInstans().getPackage(token,Accept,AcceptLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getPackage.setValue((DataPackage) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
//                Log.i("error", ""+e);
//                ResponseBody responseBody =((HttpException)e)
//                        .response().errorBody();
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(responseBody.string());
//                    ModelCondation data = new ModelCondation(object.getInt("status"),object.getInt("code"),object.getString("message"));
//                    getProfile.setValue(data);
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getTypeCompany(String Accept,String l){
        Observable observable = RetrofitAPI.getInstans().getTypeCompany(Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getTypeCompany.setValue((ModelTypeCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getCv(String Token,String Accept,String l, RequestBody Cv){
        Observable observable= RetrofitAPI.getInstans().getCV(Token,Accept,l,Cv)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //.subscribe(dataresponse -> dataCompany.setValue(dataresponse.getData()),
//                        error -> {ResponseBody responseBody =((HttpException)error)
//                        .response().errorBody();
//                            JSONObject object = new JSONObject(responseBody.string());
//
//                            Log.d("ahmed", " "+object);
//                        },
//                        () -> Log.e("ahmed","complete"));

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                CV.setValue((DataCV) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {

                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCV data = new DataCV(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        CV.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }



            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void  getPaymentguard(String Token, String Accept, String l, DataSendPayment sendPayment){
        Observable observable = RetrofitAPI.getInstans().getPayment(Token,Accept,l,sendPayment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Payment.setValue((DataPayment) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("error", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getPackageCompany(String token ,String Accept,String AcceptLanguage){
        Observable observable = RetrofitAPI.getInstans().getPackageCompany(token,Accept,AcceptLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getPackageCompany.setValue((DataPackageCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
//                Log.i("error", ""+e);
//                ResponseBody responseBody =((HttpException)e)
//                        .response().errorBody();
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(responseBody.string());
//                    ModelCondation data = new ModelCondation(object.getInt("status"),object.getInt("code"),object.getString("message"));
//                    getProfile.setValue(data);
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getOllstaff(String Token,String Accept,String l, DataSendSearchstaff searchstaff){
        Observable observable= RetrofitAPI.getInstans().getsearchstaff(Token,Accept,l,searchstaff)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getollstaff.setValue((DataOllStaff) o);


            }

            @Override
            public void onError(@NonNull Throwable e) {

                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOllStaff data = new DataOllStaff(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getollstaff.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }



            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void  getPaymentcompany(String Token, String Accept, String l, DataSendPayment sendPayment){
        Observable observable = RetrofitAPI.getInstans().getPaymentcompany(Token,Accept,l,sendPayment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Paymentcompany.setValue((DataPaymentCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataPaymentCompany data = new DataPaymentCompany(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        Paymentcompany.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  getOllNotifications(String Token,String Accept,String l){
        Observable observable = RetrofitAPI.getInstans().getollnotifications(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Notifications.setValue((DataNotifications) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.i("error", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getReasons(String Accept,String l){
        Observable observable = RetrofitAPI.getInstans().getReasons(Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Reasonslivedata.setValue((DataReasons) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.i("error", ""+e);

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getProblem(String Token, String Accept, String l, RequestBody requestBody){
        Observable observable = RetrofitAPI.getInstans().sendProblem(Token,Accept,l,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                problem.setValue((DataProblem) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataProblem data = new DataProblem(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        problem.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getguardscompany(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getguardscompany(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getguardscompany.setValue((DataPayCvs) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataPayCvs data = new DataPayCvs(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getguardscompany.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  SendCVS(String Token, String Accept, String l, ArrayList<String> Users){
        Observable observable = RetrofitAPI.getInstans().SendCvs(Token,Accept,l,Users)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                SendCvs.setValue((SendCvs) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        SendCvs data = new SendCvs(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        SendCvs.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getofferscampany(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getofferscompany(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getoffrescompany.setValue((DataOfferCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOfferCompany data = new DataOfferCompany(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getoffrescompany.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getSubscribeCompany(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getSubscribecompany(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataSubscribeCompany.setValue((DataSubscribeCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSubscribeCompany data = new DataSubscribeCompany(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        dataSubscribeCompany.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getofferguard(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getofferguard(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getoffreguard.setValue((DataOfferguard) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOfferguard data = new DataOfferguard(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getoffreguard.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
////////////////////////
    public void  getSubscribeguard(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getSubscribeguard(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataSubscribeguard.setValue((DataSubscribeCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSubscribeCompany data = new DataSubscribeCompany(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        dataSubscribeguard.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  ShowUserByUser(String Token, String Accept, String l,int id){
        Observable observable = RetrofitAPI.getInstans().ShowUserByUser(Token,Accept,l,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                ShowUserByUser.setValue((DataShowUserByUser) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataShowUserByUser data = new DataShowUserByUser(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        ShowUserByUser.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  setGetmessageguard(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getmessageguard(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getmessage.setValue((DataMassege) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataMassege data = new DataMassege(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getmessage.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
/////

    public void  getguardscompanyfilter(String Token, String Accept, String l, DataSendfilterguard sendfilterguard){
        Observable observable = RetrofitAPI.getInstans().getguardcompanyfilter(Token,Accept,l,sendfilterguard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getguardscompanyfilter.setValue((DataPayCvs) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataPayCvs data = new DataPayCvs(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getguardscompanyfilter.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  getlogout(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().getlogout(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getlogout.setValue((DataLogout) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataLogout data = new DataLogout(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getlogout.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  MessageCompany(String Token, String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().Messagecompany(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                MessageCompany.setValue((DataMessageCompany) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataMessageCompany data = new DataMessageCompany(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        MessageCompany.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  gettems(String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().gettems(Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                gettems.setValue((DataTems) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataTems data = new DataTems(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        gettems.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  WorkVatures(String Accept, String l){
        Observable observable = RetrofitAPI.getInstans().worknatures(Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                worknat.setValue((DataWorkNatures) o);
                Log.i("yes", "next");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataWorkNatures data = new DataWorkNatures(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        worknat.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  sendofferjob(String Token, String Accept, String l, SendDataOfferJob offerJob){
        Observable observable = RetrofitAPI.getInstans().Sendofferjob(Token,Accept,l,offerJob)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                dataoffersjob.setValue((DataResponseoffersjob) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataResponseoffersjob data = new DataResponseoffersjob(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        dataoffersjob.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  Reedollnav(String Token, String Accept, String l ){
        Observable observable = RetrofitAPI.getInstans().Reedollnav(Token,Accept,l)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                Reedol.setValue((Reedollnav) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Reedollnav data = new Reedollnav(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        Reedol.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  Accepted(String Token, String Accept, String l ,int id){
        Observable observable = RetrofitAPI.getInstans().Accepted(Token,Accept,l,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                Accepted.setValue((Reedollnav) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Reedollnav data = new Reedollnav(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        Accepted.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  Reject(String Token, String Accept, String l ,int id){
        Observable observable = RetrofitAPI.getInstans().Reject(Token,Accept,l,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                Reject.setValue((Reedollnav) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Reedollnav data = new Reedollnav(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        Reject.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  updateprofilcompany(String Token, String Accept, String l , RequestBody company){
        Observable observable = RetrofitAPI.getInstans().UpdateProfileCommpany(Token,Accept,l,company)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                updatecompany.setValue((Profile) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Profile data = new Profile(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        updatecompany.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  updatePassword(String Token, String Accept, String l , SendDataUpdtePassword updtePassword){
        Observable observable = RetrofitAPI.getInstans().UpdatePassword(Token,Accept,l,updtePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                updatepassword.setValue((Reedollnav) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Reedollnav data = new Reedollnav(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        updatepassword.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  updatePhhone(String Token, String Accept, String l , SendDataRecuestCode recuestCode){
        Observable observable = RetrofitAPI.getInstans().UpdatePhone(Token,Accept,l,recuestCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                updatephone.setValue((DataUpdatephone) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataUpdatephone data = new DataUpdatephone(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        updatephone.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
    public void  updateCode(String Token, String Accept, String l , SendVerifyAccoun recuestCode){
        Observable observable = RetrofitAPI.getInstans().Updatecode(Token,Accept,l,recuestCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                SendcodeUpdate.setValue((DataUpdatephone) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataUpdatephone data = new DataUpdatephone(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        SendcodeUpdate.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  updateCvguard(String Token, String Accept, String l , RequestBody sendCV){
        Observable observable = RetrofitAPI.getInstans().UpdateCvguard(Token,Accept,l,sendCV)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                UpdateCvguard.setValue((DataCV) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCV data = new DataCV(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        UpdateCvguard.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  getOllguardAcceptOffer(String Token, String Accept, String l , SendDataShowOllguardAcceptOffer ollguardAcceptOffer){
        Observable observable = RetrofitAPI.getInstans().OllguardAcceptOffer(Token,Accept,l,ollguardAcceptOffer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                showOllguardAcceptOffer.setValue((ModelShowOllguardAcceptOffer) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        ModelShowOllguardAcceptOffer data = new ModelShowOllguardAcceptOffer(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        showOllguardAcceptOffer.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  ResponceMessage(String Token, String Accept, String l , SendDataChatGrupe grupe){
        Observable observable = RetrofitAPI.getInstans().ResponceMessage(Token,Accept,l,grupe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                ResponceMessage.setValue((DataResponcemessage) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataResponcemessage data = new DataResponcemessage(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        ResponceMessage.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }



    public void  getmessageDitalse(String Token, String Accept, String l , int id){
        Observable observable = RetrofitAPI.getInstans().getmessageDitalse(Token,Accept,l,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                getmessageDitalse.setValue((DataDetilsMessage) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDetilsMessage data = new DataDetilsMessage(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        getmessageDitalse.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void  SendMessage(String Token, String Accept, String l , RequestBody body){
        Observable observable = RetrofitAPI.getInstans().SendMessage(Token,Accept,l,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                for (int i=0;i<Users.size();i++){
//                    Log.i("yes"+i, ""+Users.get(i));
//
//                }
            }

            @Override
            public void onNext(@NonNull Object o) {
                modelsendmessageMutableLiveData.setValue((Modelsendmessage) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        Modelsendmessage data = new Modelsendmessage(object.getInt("status"), object.getInt("code"), object.getString("message"));
                        modelsendmessageMutableLiveData.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }




            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

}
