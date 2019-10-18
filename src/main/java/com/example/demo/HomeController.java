package com.example.demo;
/*
Write an Spring Boot application simulating a ATM at a bank.

The application should allow people to make a deposit or a withdrawal with a form and show their account balance.

So, you will need the following pages:

Registration form
Login form (for collecting user's account number)
Deposit form
Withdrawal form
For your model, you will need at least (and possibly more)

Transaction

id # (autoincrement)
account #
action (withdrawal or deposit)
amount
Account Balance
Users should be able to create an account and use their account number to validate their credentials and make transactions (deposit or withdraw money).

Users must validate account information (password) before making each transaction.

You must have at least three users and each user must have at least two accounts and each account must contain multiple transactions.

Use Bootstrap and make it look professional.

 Done Already?

Add a transaction history reflecting each deposit/withdrawal

Publish to Heroku
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DepositRepository depositRepository;
    @Autowired
    WithdrawRepository withdrawRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String homepage(Model model) {
        model.addAttribute("deposits", depositRepository.findAll());
        model.addAttribute("withdraws", withdrawRepository.findAll());
        return "index";
    }
    @RequestMapping("/personlist")
    public String departmentList(Model model) {
        model.addAttribute("deposits", depositRepository.findAll());
        return "personlist";

    }

    @GetMapping("/addperson")
    public String personForm(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "personform";
    }

    @PostMapping("/processperson")
    public String processForm1(@Valid Deposit deposit,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "personform";
        }
        depositRepository.save(deposit);
        return "redirect:/personlist";
    }
    @RequestMapping("/petlist")
    public String employeeList(Model model){
        model.addAttribute("pets", withdrawRepository.findAll());
        return "petlist";
    }

    @GetMapping("/addpet")
    public String employeeForm(Model model){
        model.addAttribute("withdraw", new Withdraw());
        model.addAttribute("deposits", depositRepository.findAll());
        return "petform";
    }
    @PostMapping("/processpet")
    public String processForm2(@Valid Withdraw withdraw,
                               BindingResult result){
        if (result.hasErrors()){
            return "petform";
        }
        withdrawRepository.save(withdraw);
        return "redirect:/petlist";
    }
    @RequestMapping("/detailperson/{id}")
    public String showPerson(@PathVariable("id") long id, Model model)
    {model.addAttribute("person", depositRepository.findAll());
        return "showperson";
    }
    @RequestMapping("/updateperson/{id}")
    public String updatePerson(@PathVariable("id") long id,Model model){
        model.addAttribute("person", depositRepository.findById(id).get());
        return "personform";
    }
    @RequestMapping("/deleteperson/{id}")
    public String delPerson(@PathVariable("id") long id){
        depositRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/detailpet/{id}")
    public String showPet(@PathVariable("id") long id, Model model)
    {model.addAttribute("pet", withdrawRepository.findById(id).get());
        return "showpet";
    }
    @RequestMapping("/updatepet/{id}")
    public String updatePet(@PathVariable("id") long id,Model model){
        model.addAttribute("person", depositRepository.findById(id).get());
        model.addAttribute("pets", withdrawRepository.findAll());
        return "petform";
    }
    @RequestMapping("/deletepet/{id}")
    public String delPet(@PathVariable("id") long id){
        withdrawRepository.deleteById(id);
        return "redirect:/";
    }
}

