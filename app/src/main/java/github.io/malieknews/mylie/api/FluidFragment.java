import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import java.util.Locale;

/**
 * The {@link AdManagerFluidSizeFragment} demonstrates the use of the {@code AdSize.FLUID} ad size.
 */
public class AdManagerFluidSizeFragment extends Fragment {

  private AdManagerAdView adView;
  private Button changeAdViewWidthButton;
  private TextView currentWidthTextView;
  private final int[] adViewWidths = new int[]{200, 250, 320};
  private int currentIndex = 0;

  public AdManagerFluidSizeFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_gam_fluid_size, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    // The size for this AdManagerAdView is defined in the XML layout as AdSize.FLUID. It could
    // also be set here by calling adView.setAdSizes(AdSize.FLUID).
    //
    // An ad with fluid size will automatically stretch or shrink to fit the height of its
    // content, which can help layout designers cut down on excess whitespace.
    adView = getView().findViewById(R.id.fluid_av_main);

    AdManagerAdRequest request = new AdManagerAdRequest.Builder().build();
    adView.loadAd(request);

    changeAdViewWidthButton = getView().findViewById(R.id.fluid_btn_change_width);
    changeAdViewWidthButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int newWidth = adViewWidths[currentIndex % adViewWidths.length];
        currentIndex += 1;
        // Change the AdManagerAdView's width.
        ViewGroup.LayoutParams layoutParams = adView.getLayoutParams();
        final float scale = getResources().getDisplayMetrics().density;
        layoutParams.width = (int) (newWidth * scale + 0.5f);
        adView.setLayoutParams(layoutParams);
        // Update the TextView with the new width.
        currentWidthTextView = getView().findViewById(R.id.fluid_tv_current_width);
        currentWidthTextView.setText(
            String.format(Locale.getDefault(), "%d dp", newWidth));
      }
    });
  }
}
