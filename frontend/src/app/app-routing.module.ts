import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventsComponent } from './events/events.component';
import { GamesComponent } from './games/games.component';
import { SignupComponent } from './signup/signup.component';


const routes: Routes = [
  {path: "sign-up", component: SignupComponent},
  {path: "games", component: GamesComponent},
  {path: "events", component: EventsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
