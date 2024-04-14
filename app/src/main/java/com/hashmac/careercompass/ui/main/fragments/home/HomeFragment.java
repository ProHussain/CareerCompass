package com.hashmac.careercompass.ui.main.fragments.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentHomeBinding;

import org.checkerframework.checker.units.qual.A;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initClickListeners();
    }

    private void initClickListeners() {
        binding.btnJoinNow.setOnClickListener(v -> joinNow());
        binding.cardAndroid.setOnClickListener(v -> openInfoDialog("Android Development", "Android development is the process by which new applications are created for devices running the Android operating system. Applications are usually developed in Java programming language using the Android software development kit (SDK), but other development environments are also available.", "https://developer.android.com/"));
        binding.cardIOS.setOnClickListener(v -> openInfoDialog("iOS Development", "iOS development is the process of creating mobile applications for Apple hardware, including iPhone, iPad and iPod Touch. The software is written in the Swift programming language or Objective-C and then deployed to the App Store for users to download.", "https://developer.apple.com/"));
        binding.cardWeb.setOnClickListener(v -> openInfoDialog("Web Development", "Web development is the work involved in developing a Web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing a simple single static page of plain text to complex Web-based Internet applications (Web apps), electronic businesses, and social network services.", "https://developer.mozilla.org/en-US/docs/Learn"));
        binding.cardDropshipping.setOnClickListener(v -> openInfoDialog("Dropshipping", "Dropshipping is a retail fulfillment method where a store doesn't keep the products it sells in stock. Instead, when a store sells a product, it purchases the item from a third party and has it shipped directly to the customer. As a result, the merchant never sees or handles the product.", "https://www.shopify.com/guides/dropshipping"));
        binding.cardPrintOnDemand.setOnClickListener(v -> openInfoDialog("Print On Demand", "Print on demand is a business model that allows printing small quantities of custom merchandise with no inventory, using print on demand services. This means that you can design merchandise like t-shirts, mugs, and phone cases, and sell them online without having to keep any stock.", "https://www.printful.com/"));
        binding.cardCustomCake.setOnClickListener(v -> openInfoDialog("Custom Cake", "Custom cakes are cakes that are made to order. They are designed to meet the specific needs of the customer, and can be made in a variety of shapes, sizes, and flavors. Custom cakes are often used for special occasions such as birthdays, weddings, and anniversaries.", "https://www.wilton.com/"));
        binding.cardFreelanceWriter.setOnClickListener(v -> openInfoDialog("Freelance Writer", "Freelance writing is the practice of writing for money while working on one's own and not being employed by a company or organization. Freelance writers produce whatever written text is needed by their clients, either working from home or in a rented office space.", "https://www.freelancewriting.com/"));
        binding.cardVirtualAssistant.setOnClickListener(v -> openInfoDialog("Virtual Assistant", "A virtual assistant is a self-employed worker who specializes in offering administrative services to clients from a remote location, usually a home office. Common tasks a virtual assistant might perform include scheduling appointments, making phone calls, making travel arrangements, and managing email accounts.", "https://www.thevirtualhub.com/what-is-a-virtual-assistant/"));
        binding.cardPhotographer.setOnClickListener(v -> openInfoDialog("Photographer", "A photographer is a professional that focuses on the art of taking photographs with a digital or film camera. Photographers use artificial and/or natural lighting to snap pictures of various people, places and things in a variety of settings.", "https://www.britannica.com/technology/photography"));
    }

    private void openInfoDialog(String title, String message, String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title);
        builder.setMessage(message +
                "The average salary for this field in Pakistan is Rs 30,000 per month. While starting salary is Rs 10,000 per month. It's a great field to start your career in programming with a lot of opportunities. You can learn more about it by joining our live session on Career Compass or follow below link");
        builder.setPositiveButton("Learn More", (dialog, which) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void joinNow() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://wa.me/923016907146?text=I%20want%20to%20join%20Career%20Compass%20live%20session"));
        startActivity(intent);
    }
}
