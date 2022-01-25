package com.example.shut_fe.models.preference

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
@Entity(tableName = "preference")
data class Preference(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var _id: Int? = null,
    @ColumnInfo(name = "user_id")
    private var _userId: Int? = null,
    @ColumnInfo(name = "max_sound")
    private var _maxSound: String? = null,
    @ColumnInfo(name = "max_vibration")
    private var _maxVibration: String? = null,
    @ColumnInfo(name = "sound_control")
    private var _soundControl: Boolean? = null,
    @ColumnInfo(name = "color_alert")
    private var _colorAlert: Boolean? = null,
    @ColumnInfo(name = "sound_alert")
    private var _soundAlert: Boolean? = null,
    @ColumnInfo(name = "music")
    private var _music: Int? = null,
    @ColumnInfo(name = "volume")
    private var _volume: Int? = null,
) : Parcelable, BaseObservable() {

    var id: Int?
        @Bindable get() = _id
        set(value) {
            _id = value
            notifyPropertyChanged(BR.id)
        }

    var userId: Int?
        @Bindable get() = _userId
        set(value) {
            _userId = value
            notifyPropertyChanged(BR.userId)
        }

    var maxSound: String?
        @Bindable get() = _maxSound
        set(value) {
            _maxSound = value
            notifyPropertyChanged(BR.maxSound)
        }
    var maxVibration: String?
        @Bindable get() = _maxVibration
        set(value) {
            _maxVibration = value
            notifyPropertyChanged(BR.maxVibration)
        }

    var soundControl: Boolean?
        @Bindable get() = _soundControl
        set(value) {
            _soundControl = value
            notifyPropertyChanged(BR.soundControl)
        }
    var colorAlert: Boolean?
        @Bindable get() = _colorAlert
        set(value) {
            _colorAlert = value
            notifyPropertyChanged(BR.colorAlert)
        }
    var soundAlert: Boolean?
        @Bindable get() = _soundAlert
        set(value) {
            _soundAlert = value
            notifyPropertyChanged(BR.soundAlert)
        }
    var music: Int?
        @Bindable get() = _music
        set(value) {
            _music = value
            notifyPropertyChanged(BR.music)
        }
    var volume: Int?
        @Bindable get() = _volume
        set(value) {
            _volume = value
            notifyPropertyChanged(BR.volume)
        }
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(_id)
        parcel.writeValue(_userId)
        parcel.writeString(_maxSound)
        parcel.writeString(_maxVibration)
        parcel.writeValue(_soundControl)
        parcel.writeValue(_colorAlert)
        parcel.writeValue(_soundAlert)
        parcel.writeValue(_music)
        parcel.writeValue(_volume)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Preference> {
        override fun createFromParcel(parcel: Parcel): Preference {
            return Preference(parcel)
        }

        override fun newArray(size: Int): Array<Preference?> {
            return arrayOfNulls(size)
        }
    }
}
