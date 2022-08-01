import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SubscribeService } from '../subscribe.service';
import { SubscriptionDetails } from '../subscription-details';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  constructor(private eservice: SubscribeService, private route: Router, private authService: AuthService) {

    if(!this.authService.isLoggedIn()){
      this.route.navigate(["/"]);
    }
  }

  subscribed: boolean = false

  mId:string= localStorage.getItem("userId") || " ";
  date!: Date
  subscriptions: SubscriptionDetails[] = []

  ngOnInit(): void {
    this.subscribed = false
    this.getAllSubscriptions();
  }

  refill(sid: string) {
    console.log(sid + '******')
    localStorage.setItem('subID', sid);
    this.route.navigate(['/refill']);

  }


  getAllSubscriptions() {
    this.subscriptions = []
    console.log(this.mId);
    console.log(localStorage.getItem("userId"));
    this.eservice.getAllSubscriptions(this.mId).subscribe(data => {
      this.subscribed = true
      this.subscriptions = data as SubscriptionDetails[];
      console.log(data)

    },error=>{
      console.log(error);
    })
  }
  msg: string = ''
  unsubed: boolean = false
  unsubscribe(sId: string) {
    this.eservice.unsubscribe(this.mId, sId as unknown as number).subscribe(data => {
      this.subscribed = false
      this.unsubed = true
      // this.getAllSubscriptions()
      console.log(data);
      if(data=="Clear Payments")this.msg="Please clear the due payments before unsubscribing"
      else this.msg="You have successfully unsubscribed"
      // this.route.navigate(['home'])
      console.log(sId)
    }, error => {
      console.log(error);

      // window.location.reload()
    })
  }

  showsubscriptions() {
    this.subscribed = false
    this.unsubed = false

    this.getAllSubscriptions()
  }

}
