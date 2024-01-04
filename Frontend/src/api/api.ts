export * from './auth.service';
import { AuthService } from './auth.service';
export * from './default.service';
import { DefaultService } from './default.service';
export * from './player.service';
import { PlayerService } from './player.service';
export * from './search.service';
import { SearchService } from './search.service';
export * from './session.service';
import { SessionService } from './session.service';
export const APIS = [AuthService, DefaultService, PlayerService, SearchService, SessionService];
