import { Routes } from '@angular/router';
import { AdminSessionsComponent } from './admin-sessions/admin-sessions.component'
import { AuthorizationComponent } from './authorization/authorization.component'
import { SessionComponent } from './session/session.component';
import { SessionSummaryComponent } from './session-summary/session-summary.component';

export const routes: Routes = [
    {path: '', redirectTo: '/authorization', pathMatch: "full"},
    {path: 'authorization/:pin', component: AuthorizationComponent},
    {path: 'callback', component: AdminSessionsComponent},
    {path: 'admin-sessions', component: AdminSessionsComponent},
    {path: 'authorization', component: AuthorizationComponent },
    {path: 'session/:id', component: SessionComponent},
    {path: 'session/:id/summary', component: SessionSummaryComponent}
];