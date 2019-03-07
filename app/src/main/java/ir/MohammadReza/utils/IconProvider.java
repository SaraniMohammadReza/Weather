package ir.MohammadReza.utils;


import ir.MohammadReza.R;

public class IconProvider {

    public static int getImageIcon(String weatherDescription){
        int weatherIcon ;
        switch(weatherDescription) {
            case "Thunderstorm":
                weatherIcon = R.drawable.radobargh;
                break;
            case "Drizzle":
                weatherIcon = R.drawable.barani_abri;
                break;
            case "Rain":
                weatherIcon = R.drawable.barani;
                break;
            case "Snow":
                weatherIcon = R.drawable.barfi;
                break;
            case "Atmosphere":
                weatherIcon = R.mipmap.ic_atmosphere;
                break;
            case "Clear":
                weatherIcon = R.drawable.aftabi;
                break;
            case "Clouds":
                weatherIcon = R.drawable.tamam_abri;
                break;
            case "Extreme":
                weatherIcon = R.mipmap.ic_extreme;
                break;
            default:
                weatherIcon = R.mipmap.ic_launcher;
        }
        return weatherIcon;

    }

}
