package springboot.rentACarApp.Core.Redis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {
    @Cacheable(cacheNames = "OTP")
    public int otpExpired(int otp) throws InterruptedException {
        return otp;
    }
    @CacheEvict(cacheNames = "OTP")
    public int ClearCache(int otp){
        Timer();
        return otp;
    }
    public void Timer(){
        int count = 60;
        while (count > 0) {
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }
    }
}
