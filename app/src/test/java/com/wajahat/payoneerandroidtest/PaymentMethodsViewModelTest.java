package com.wajahat.payoneerandroidtest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.wajahat.payoneerandroidtest.api.PaymentMethodsRepository;
import com.wajahat.payoneerandroidtest.api.WebService;
import com.wajahat.payoneerandroidtest.data.model.ApplicableNetwork;
import com.wajahat.payoneerandroidtest.data.model.Networks;
import com.wajahat.payoneerandroidtest.data.response.GetPaymentMethodsResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@RunWith(JUnit4.class)
public class PaymentMethodsViewModelTest {

    @Rule
    public RxImmediateSchedulerRule testSchedulerRule = new RxImmediateSchedulerRule();
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    public WebService service = mock(WebService.class);
    public PaymentMethodsRepository repository = new PaymentMethodsRepository(service);

    @Test
    public void testPaymentMethods_Repo_Response() throws MalformedURLException {
        Networks networks = new Networks();
        networks.setApplicable(generateDummyApplicableNetworksList());
        GetPaymentMethodsResponse response = new GetPaymentMethodsResponse();
        response.setNetworks(networks);
        when(service.getPaymentMethods())
                .thenReturn(Observable.just(response));

        Observable<List<ApplicableNetwork>> result =
                repository.getPaymentMethods()
                        .flatMap(response1 -> Observable.just(response1.getNetworks().getApplicable()));
        TestObserver<List<ApplicableNetwork>> testObserver = new TestObserver<>();

        result.subscribe(testObserver);

        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        List<ApplicableNetwork> applicableNetworks = testObserver.values().get(0);

        // Size
        assertThat(applicableNetworks, hasSize(2));
        assertThat(applicableNetworks.get(0).getLabel(), is("American Express"));
        assertNotNull(applicableNetworks.get(0).getLinks().get("logo"));
    }

    private static List<ApplicableNetwork> generateDummyApplicableNetworksList() throws MalformedURLException {
        // American Express
        HashMap<String, URL> linksAmex = new HashMap<>();
        linksAmex.put("logo", new URL("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png"));
        linksAmex.put("self", new URL("https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX"));

        HashMap<String, URL> linksDiners = new HashMap<>();
        linksDiners.put("logo", new URL("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/diners.png"));
        linksDiners.put("self", new URL("https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/DINERS"));

        List<ApplicableNetwork> list = new ArrayList<>();
        list.add(new ApplicableNetwork("American Express", linksAmex));
        list.add(new ApplicableNetwork("Diners Club", linksDiners));

        return list;
    }
}