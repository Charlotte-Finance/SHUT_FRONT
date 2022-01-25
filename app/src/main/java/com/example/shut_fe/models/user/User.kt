package com.example.shut_fe.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shut_fe.BR

@Keep
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var _id: Int? = null,
    @ColumnInfo(name = "email")
    private var _email: String? = null,
    @ColumnInfo(name = "password")
    private var _password: String? = null,
    private var _repeatPassword: String? = null,
    @ColumnInfo(name = "forename")
    private var _forename: String? = null,
    @ColumnInfo(name = "last_name")
    private var _lastName: String? = null,
    @ColumnInfo(name = "age")
    private var _age: String? = null,
    @ColumnInfo(name = "address")
    private var _address: String? = null,
    @ColumnInfo(name = "city")
    private var _city: String? = null,
    @ColumnInfo(name = "country")
    private var _country: String? = null,
    @ColumnInfo(name = "gender")
    private var _gender: String? = null,


    ) : Parcelable, BaseObservable() {

    var id: Int?
        @Bindable
        get() = _id
        set(value) {
            _id = value
            notifyPropertyChanged(BR.id)
        }
    var email: String?
        @Bindable
        get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }
    var password: String?
        @Bindable
        get() = _password
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }
    var repeatPassword: String?
        @Bindable
        get() = _repeatPassword
        set(value) {
            _repeatPassword = value
            notifyPropertyChanged(BR.repeatPassword)
        }
    var forename: String?
        @Bindable
        get() = _forename
        set(value) {
            _forename = value
            notifyPropertyChanged(BR.forename)
        }
    var lastName: String?
        @Bindable
        get() = _lastName
        set(value) {
            _lastName = value
            notifyPropertyChanged(BR.lastName)
        }
    var age: String?
        @Bindable
        get() = _age
        set(value) {
            _age = value
            notifyPropertyChanged(BR.age)
        }
    var address: String?
        @Bindable
        get() = _address
        set(value) {
            _address = value
            notifyPropertyChanged(BR.address)
        }
    var city: String?
        @Bindable
        get() = _city
        set(value) {
            _city = value
            notifyPropertyChanged(BR.city)
        }
    var country: String?
        @Bindable
        get() = _country
        set(value) {
            _country = value
            notifyPropertyChanged(BR.country)
        }
    var gender: String?
        @Bindable
        get() = _gender
        set(value) {
            _gender = value
            notifyPropertyChanged(BR.gender)
        }

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(_id)
        parcel.writeString(_email)
        parcel.writeString(_password)
        parcel.writeString(_repeatPassword)
        parcel.writeString(_forename)
        parcel.writeString(_lastName)
        parcel.writeValue(_age)
        parcel.writeString(_address)
        parcel.writeString(_city)
        parcel.writeString(_country)
        parcel.writeString(_gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

