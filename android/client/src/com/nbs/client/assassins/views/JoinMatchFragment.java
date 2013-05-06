package com.nbs.client.assassins.views;


import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.nbs.client.assassins.R;
import com.nbs.client.assassins.R.id;
import com.nbs.client.assassins.R.layout;
import com.nbs.client.assassins.models.User;
import com.nbs.client.assassins.network.HuntedRestClient;
import com.nbs.client.assassins.network.JoinMatchMessage;
import com.nbs.client.assassins.network.MatchResponse;
import com.nbs.client.assassins.network.Response;

@EFragment(R.layout.join_match_fragment)
public class JoinMatchFragment extends SherlockFragment {

    // Container Activity must implement this interface
    public interface OnMatchJoinedListener {
        public void onMatchJoined(boolean wasJoind);
    }

	private static final String TAG = "JoinMatchFragment";

	private static final int MIN_PASSWORD_LEN = 6;
	private static final int MIN_MATCH_NAME_LEN = 6;
	
    OnMatchJoinedListener mListener;
	
	@ViewById(R.id.join_match)
	Button btnJoin;
	
	@ViewById(R.id.edit_join_match_name)
	EditText matchName;
	
	@ViewById(R.id.edit_join_match_password)
	EditText password;
	
	//@ViewById(R.id.join_when_join_match)
	//Switch join;
	
	@RestService
	HuntedRestClient restClient;
	
	private ProgressDialog asyncProgress;
	
	public JoinMatchFragment() {
		
	}
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMatchJoinedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnMatchJoindListener");
        }
    }
    
	@AfterInject
	public void afterInjection() {
		//subvert a bug in HttpUrlConnection
		//see: http://www.sapandiwakar.in/technical/eofexception-with-spring-rest-template-android/
		restClient.getRestTemplate().setRequestFactory(
				new HttpComponentsClientHttpRequestFactory());
	}

	@Click(R.id.join_match)
	void onJoinMatchClicked() {

		//TODO: validate name and password before allowing button to be enabled
		//TODO: show visual indication if there are validation issues
		Log.i(TAG, password.getText().toString());
		String passwordStr = password.getText().toString();
		if((passwordStr.length() >= MIN_PASSWORD_LEN || passwordStr.length() == 0)  && 
		   matchName.getText().toString().length() >= MIN_MATCH_NAME_LEN) {
			
			InputMethodManager imm = (InputMethodManager)getSherlockActivity().getSystemService(
				      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
			btnJoin.setEnabled(false);
			
			JoinMatchMessage msg = new JoinMatchMessage();
			
			msg.userToken = User.getToken(getActivity());
			msg.matchPassword = passwordStr.length() > 0 ? passwordStr : null;
			msg.matchName = matchName.getText().toString();
			
			asyncProgress = new ProgressDialog(getActivity());
			asyncProgress.setIndeterminate(true);
			asyncProgress.setTitle("Please Wait");
			asyncProgress.setMessage("Joining match...");
			asyncProgress.setCancelable(false);
			asyncProgress.show();
			
			joinMatchInBackground(msg);
		}
		else {
			//TODO: provide earlier and better validation information to user
			Toast.makeText(
				getActivity(), 
				"Match name at least " + MIN_MATCH_NAME_LEN +
				" chars, Password at least " + MIN_PASSWORD_LEN + " chars", 
				Toast.LENGTH_LONG
			).show();
		}
	}
	
	@Background
	void joinMatchInBackground(JoinMatchMessage msg) {
		
		MatchResponse response = null;
		
		try {	
			//TODO: handle exceptions
			response = restClient.joinMatch(msg.matchName, msg);		
		}
		catch(Exception e) {
			Log.i(TAG, "EXCEPTION: " + e.toString());
			//Log.i(TAG, e.getMessage());
			//Log.i(TAG, e.getCause().getMessage());
			//for(StackTraceElement el : e.getStackTrace())
			//	Log.e(TAG, el.toString());
		}
		
		matchJoinedResult(response);
	}
	
	@UiThread
	void matchJoinedResult(MatchResponse response) {
		asyncProgress.dismiss();
		
		if(response != null) {
			
			Toast.makeText(getActivity(), response.message, Toast.LENGTH_SHORT).show();
			
			Log.d(TAG, response.toString());
			
			if(response.ok()) {
				User.setMatch(getActivity(), response.match);
				mListener.onMatchJoined(true);
				return;
			}
			
				
			
		} else {
			Toast.makeText(getActivity(), "Network error.", Toast.LENGTH_LONG).show();
		}
		
		btnJoin.setEnabled(true);
	}
}