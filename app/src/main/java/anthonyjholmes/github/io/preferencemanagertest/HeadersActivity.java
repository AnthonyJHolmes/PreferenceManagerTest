package anthonyjholmes.github.io.preferencemanagertest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import java.util.List;

/**
 * Created by ajh3 on 09/02/2016.
 */
public class HeadersActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target){
        loadHeadersFromResource(R.xml.preference_headers, target);
    }
    @Override
    protected boolean isValidFragment(String fragmentName) {
        return HeadersActivity.HeadersFragment.class.getName().equals(fragmentName);
    }
    public static class HeadersFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener{

        @Override
        public void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           String settings = getArguments().getString("header");

               if("one".equals(settings)){
                   addPreferencesFromResource(R.xml.preference_header_one);
               }
               else if("two".equals(settings)){
                   addPreferencesFromResource(R.xml.preference_header_two);
               }

        }

        @Override
        public void onResume(){
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        }

        @Override
        public void onPause(){
            super.onPause();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
            if(key.equals("food_preference")){
                Preference foodPref= findPreference(key);
                foodPref.setSummary(sharedPreferences.getString(key,""));
            }
        }
    }
}
