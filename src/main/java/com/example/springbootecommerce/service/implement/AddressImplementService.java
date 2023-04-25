package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.UserNotFoundException;
import com.example.springbootecommerce.pojo.entity.Address;
import com.example.springbootecommerce.pojo.entity.Role;
import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.AddressRequest;
import com.example.springbootecommerce.repository.AddressRepository;
import com.example.springbootecommerce.repository.RoleRepository;
import com.example.springbootecommerce.repository.UserRepository;
import com.example.springbootecommerce.service.AddressService;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class AddressImplementService implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address saveAddress(AddressRequest addressRequest) throws IOException {
        User user = userRepository.findUserById(addressRequest.getUserId());
        if(user == null){
            throw new UserNotFoundException();
        }
        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setName_receiver(addressRequest.getName_receiver());
        address.setPhone(addressRequest.getPhone());
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

    @Override
    public Address updateAddress(AddressRequest addressRequest) throws IOException {
        System.out.println(addressRequest.getUserId());
        Address address = addressRepository.findAddressByUserId(addressRequest.getUserId());
        System.out.println(address);
        if(address == null) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findUserById(addressRequest.getUserId());

        address.setAddress(addressRequest.getAddress());
        address.setName_receiver(addressRequest.getName_receiver());
        address.setPhone(addressRequest.getPhone());
        address.setUser(user);
        // No need to call addressRepository.save(address) as JPA will automatically track and update the changes when the transaction is committed

        return addressRepository.save(address); // Save and return the updated address
    }

    @Override
    public Address findAddressById(AddressRequest addressRequest) throws IOException {
        Address address = addressRepository.findAddressByUserId(addressRequest.getUserId());
        return address;
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findAddressById(id);
        if(address == null){
            throw new UserNotFoundException("Address not found id ="+id);
        }
        addressRepository.deleteById(id);
    }

    @Override
    public Address findAddressByUserId(Long uid) {
        Address address = addressRepository.findAddressByUserId(uid);
        return address;
    }

}
